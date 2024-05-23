package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.request.APIGatewayProxyRequestEventDTO;
import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.dtos.request.RequestRoutingIntegrationDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.dtos.routinintegration.RoutingIntegrationDTO;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.RequestChangeOrderStatusDTOMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.changeorderstatus.ChangeOrderStatusUseCase;
import com.robinfood.usecases.changeorderstatus.IChangeOrderStatusUseCase;
import com.robinfood.usecases.routingintegration.IRoutingIntegrationUseCase;
import com.robinfood.usecases.routingintegration.RoutingIntegrationUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import com.robinfood.util.ValidatorFieldsUtil;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.constants.Constants.AUTHORIZATION;
import static com.robinfood.constants.Constants.DEFAULT_BOOLEAN_FALSE;
import static com.robinfood.constants.Constants.STATUS_CHANGE_PUBLISHED;
import static com.robinfood.enums.AppLogsTraceEnum.CHANGE_ORDER_STATUS_START;
import static com.robinfood.enums.AppLogsTraceEnum.PROGRAM_START;
import static com.robinfood.enums.AppLogsTraceEnum.ROUTING_INSERT_DATA;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class RoutingIntegrationHandler implements RequestHandler<APIGatewayProxyRequestEventDTO,
        ApiGatewayResponseDTO> {

    private final IChangeOrderStatusUseCase changeOrderStatusUseCase;

    private final IRoutingIntegrationUseCase routingIntegrationUseCase;

    public RoutingIntegrationHandler(
            IChangeOrderStatusUseCase changeOrderStatusUseCase,
            IRoutingIntegrationUseCase routingIntegrationUseCase
    ) {
        this.changeOrderStatusUseCase = changeOrderStatusUseCase;
        this.routingIntegrationUseCase = routingIntegrationUseCase;
    }

    public RoutingIntegrationHandler() {
        this.changeOrderStatusUseCase = new ChangeOrderStatusUseCase();
        this.routingIntegrationUseCase = new RoutingIntegrationUseCase();
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(APIGatewayProxyRequestEventDTO requestEvent, Context context) {

        LogsUtil.getInstance(context);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        try {

            String authorization = requestEvent.getHeaders().get(AUTHORIZATION);

            JwtMiddleware.validateToken(authorization);

            LogsUtil.info(PROGRAM_START.getMessageWithCode(),
                    ObjectMapperSingleton.objectToJson(requestEvent.getBody()));

            final RequestRoutingIntegrationDTO requestRoutingIntegrationDTO = ObjectMapperSingleton.jsonToClass(
                    requestEvent.getBody(),
                    RequestRoutingIntegrationDTO.class
            );

            ValidatorFieldsUtil.validateNullFields(requestRoutingIntegrationDTO);

            LogsUtil.info(
                    ROUTING_INSERT_DATA.getMessageWithCode(),
                    requestRoutingIntegrationDTO.getOrderUuid(),
                    RequestRoutingIntegrationDTO.class.getSimpleName(),
                    ObjectMapperSingleton.objectToJson(requestRoutingIntegrationDTO)
            );

            RoutingIntegrationDTO routingIntegrationDTO = routingIntegrationUseCase.invoke(
                    requestRoutingIntegrationDTO.getChannelId(),
                    authorization,
                    requestRoutingIntegrationDTO.getOrderUuid()
            );

            RequestChangeOrderStatusDTO requestChangeOrderStatusDTO = RequestChangeOrderStatusDTOMapper
                    .buildToRequestChangeOrderStatusDTO(requestRoutingIntegrationDTO);

            LogsUtil.info(CHANGE_ORDER_STATUS_START.getMessageWithCode(), routingIntegrationDTO.getUrl());

            CompletableFuture<Void> changeRun = CompletableFuture.runAsync(() ->
                    changeOrderStatusUseCase.invoke(
                            requestChangeOrderStatusDTO,
                            routingIntegrationDTO.getUrl(),
                            authorization
                    ));

            changeRun.join();

            final ResponseDTO responseBody = ResponseDTO.builder()
                    .data(requestChangeOrderStatusDTO)
                    .code(HttpStatusConstant.SC_OK.getCodeHttp())
                    .message(STATUS_CHANGE_PUBLISHED)
                    .error(DEFAULT_BOOLEAN_FALSE)
                    .build();

            apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstant.SC_OK.getCodeHttp())
                    .setObjectBody(responseBody)
                    .build();

        } catch (ApplicationException e) {
            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e, e.getStackTrace(),
                    ObjectMapperSingleton.objectToJson(e.getApiGatewayResponseDTO())
            );

            apiGatewayResponseDTO = e.getApiGatewayResponseDTO();
        }
        return apiGatewayResponseDTO;
    }
}
