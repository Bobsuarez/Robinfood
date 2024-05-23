package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.robinfood.constants.GeneralConstants;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.publisher.SendMessageSQS;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingletonUtil;
import com.robinfood.util.ValidatorFieldsUtil;

import java.util.Map;

import static com.robinfood.constants.GeneralConstants.MESSAGE_COUNTRY_HEADER;
import static com.robinfood.constants.GeneralConstants.MESSAGE_FROM_HEADER;
import static com.robinfood.enums.AppLogsTraceEnum.MESSAGE_SENT_SUCCESSFULLY;
import static com.robinfood.enums.AppLogsTraceEnum.PROGRAM_START;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class ResponseOrderMsgPubDidiHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    private final SendMessageSQS sendMessageSQS;

    public ResponseOrderMsgPubDidiHandler(SendMessageSQS sendMessageSQS) {
        this.sendMessageSQS = sendMessageSQS;
    }

    public ResponseOrderMsgPubDidiHandler() {
        sendMessageSQS = new SendMessageSQS();
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(Map<String, Object> requestEvent, Context context) {

        APIGatewayProxyRequestEvent proxyRequestEvent =
                ObjectMapperSingletonUtil.objectToClassConvertValue(requestEvent, APIGatewayProxyRequestEvent.class);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        LogsUtil.getInstance(context);

        try {

            final String authorization = proxyRequestEvent.getHeaders().get(GeneralConstants.AUTHORIZATION);
            final String messageFrom = proxyRequestEvent.getHeaders().get(GeneralConstants.MESSAGE_FROM_HEADER);
            final String messageCountry = proxyRequestEvent.getHeaders().get(GeneralConstants.MESSAGE_COUNTRY_HEADER);

            ValidatorFieldsUtil.validateAttributes(
                    proxyRequestEvent.getHeaders(),
                    MESSAGE_FROM_HEADER,
                    MESSAGE_COUNTRY_HEADER
            );

            JwtMiddleware.validateToken(authorization);

            final String jsonOrder = proxyRequestEvent.getBody();

            LogsUtil.info(PROGRAM_START.getMessage(), ObjectMapperSingletonUtil.objectToJson(jsonOrder));

            final OrderToCreateDTO orderToCreateDTO = ObjectMapperSingletonUtil.jsonToClass(
                    jsonOrder,
                    OrderToCreateDTO.class
            );

            ValidatorFieldsUtil.validateNullFields(orderToCreateDTO,
                    "company", "device", "orders", "origin", "paid", "paymentMethods", "user", "uuid");

            sendMessageSQS.invoke(orderToCreateDTO, messageFrom, messageCountry);

            LogsUtil.info(MESSAGE_SENT_SUCCESSFULLY.getMessage(),
                    ObjectMapperSingletonUtil.objectToJson(orderToCreateDTO));

            return ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstant.SC_CREATED.getCodeHttp())
                    .setObjectBody(ResponseMapper
                            .buildDefault(
                                    HttpStatusConstant.SC_CREATED.getCodeHttp(),
                                    orderToCreateDTO,
                                    AppLogsTraceEnum.RESPONSE_HANDLER.getMessage()
                            )
                    )
                    .build();

        } catch (ApplicationException e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e, e.getStackTrace(),
                    ObjectMapperSingletonUtil.objectToJson(e.getApiGatewayResponseDTO())
            );

            apiGatewayResponseDTO = e.getApiGatewayResponseDTO();
        }

        return apiGatewayResponseDTO;
    }
}
