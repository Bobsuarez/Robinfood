package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.request.RequestPublishEventDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.queues.publisher.ChangeStatusOrderPublisher;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingletonUtil;
import com.robinfood.utils.ValidatorFieldsUtil;

import java.util.Map;

import static com.robinfood.constants.Constants.AUTHORIZATION;
import static com.robinfood.enums.AppLogsTraceEnum.PROGRAM_START;
import static com.robinfood.enums.AppLogsTraceEnum.RESPONSE_HANDLER;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class ChangeStatusPubPosHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    @Override
    public ApiGatewayResponseDTO handleRequest(Map<String, Object> requestEvent, Context context) {

        APIGatewayProxyRequestEvent proxyRequestEvent =
                ObjectMapperSingletonUtil.objectToClassConvertValue(requestEvent, APIGatewayProxyRequestEvent.class);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        LogsUtil.getInstance(context);

        try {

            final String authorization = proxyRequestEvent.getHeaders().get(AUTHORIZATION);

            JwtMiddleware.validateToken(authorization);

            final String body = proxyRequestEvent.getBody();

            final RequestPublishEventDTO requestPublishEventDTO = ObjectMapperSingletonUtil.jsonToClass(
                    body,
                    RequestPublishEventDTO.class
            );

            ValidatorFieldsUtil.validateNullFields(
                    requestPublishEventDTO,
                    "eventId", "orderId", "orderUuid", "statusCode", "transactionId", "transactionUuid");

            LogsUtil.info(PROGRAM_START.getMessage(), proxyRequestEvent.getBody());

            ChangeStatusOrderPublisher.sendMessage(
                    requestPublishEventDTO
            );

            LogsUtil.info(RESPONSE_HANDLER.getMessage(),
                    ObjectMapperSingletonUtil.objectToJson(requestPublishEventDTO));

            apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstant.SC_OK.getCodeHttp())
                    .setObjectBody(ResponseMapper
                            .buildDefault(HttpStatusConstant.SC_OK.getCodeHttp(),
                                    requestPublishEventDTO,
                                    AppLogsTraceEnum.RESPONSE_HANDLER.getMessage())
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
