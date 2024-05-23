package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.request.RequestChangeStateDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ResponseDTOMapper;
import com.robinfood.security.JwtSecurity;
import com.robinfood.usecases.ISendMessageUseCase;
import com.robinfood.usecases.SendMessageUseCase;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingletonUtil;
import com.robinfood.utils.ValidatorFieldsUtil;

import java.util.Map;

import static com.robinfood.constants.Constants.AUTHORIZATION;
import static com.robinfood.constants.Constants.SUCCESSFUL;
import static com.robinfood.enums.AppLogsTraceEnum.PROGRAM_START;
import static com.robinfood.enums.AppLogsTraceEnum.RESPONSE_HANDLER;
import static com.robinfood.enums.AppLogsTraceEnum.ROUTING_INTEGRATION_START_ECS;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.utils.ValidatorFieldsUtil.getStoreId;

public class ChangeOrderStatusHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    private final ISendMessageUseCase sendMessageUseCase;

    public ChangeOrderStatusHandler(ISendMessageUseCase sendMessageUseCase) {
        this.sendMessageUseCase = sendMessageUseCase;
    }

    public ChangeOrderStatusHandler() {
        this.sendMessageUseCase = new SendMessageUseCase();
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(Map<String, Object> requestEventDTO, Context context) {

        APIGatewayProxyRequestEvent proxyRequestEvent =
                ObjectMapperSingletonUtil.objectToClassConvertValue(requestEventDTO, APIGatewayProxyRequestEvent.class);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        LogsUtil.getInstance(context);

        try {

            final String authorization = proxyRequestEvent.getHeaders().get(AUTHORIZATION);

            JwtSecurity.validateToken(authorization);

            final String jsonOrder = proxyRequestEvent.getBody();

            LogsUtil.info(PROGRAM_START.getMessage(), ObjectMapperSingletonUtil.objectToJson(jsonOrder));

            final RequestChangeStateDTO requestDTO = ObjectMapperSingletonUtil.jsonToClass(
                    jsonOrder,
                    RequestChangeStateDTO.class
            );

            ValidatorFieldsUtil.validateNullFields(requestDTO, "notes", "orderId", "origin", "statusCode");

            LogsUtil.info(ROUTING_INTEGRATION_START_ECS.getMessage());

            sendMessageUseCase.invoke(requestDTO, getStoreId(jsonOrder));

            LogsUtil.info(RESPONSE_HANDLER.getMessage(), requestDTO);

            apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstant.SC_OK.getCodeHttp())
                    .setObjectBody(ResponseDTOMapper
                            .buildDefault(HttpStatusConstant.SC_OK.getCodeHttp(), requestDTO, SUCCESSFUL))
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
