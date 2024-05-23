package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.geteventflow.request.EventFlowRequestDTO;
import com.robinfood.dtos.geteventflow.response.ResponseEventFlowDTO;
import com.robinfood.dtos.request.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.DataNotFoundException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.geteventsflow.GetEventsFlowUseCase;
import com.robinfood.usecases.geteventsflow.IGetEventsFlowUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.UUID;
import static com.robinfood.enums.AppLogsEnum.DATA_RESPONSE_REPOSITORY;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_DATA_NOT_FOUND;


public class EventFlowHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    private final IGetEventsFlowUseCase getEventsFlowUseCase;

    public EventFlowHandler(IGetEventsFlowUseCase getEventsFlowUseCase) {
        this.getEventsFlowUseCase = getEventsFlowUseCase;
    }

    public EventFlowHandler() {
        this.getEventsFlowUseCase = new GetEventsFlowUseCase();
    }


    @Override
    public ApiGatewayResponseDTO handleRequest(Map<String, Object> requestEvent, Context context) {

        APIGatewayProxyRequestEvent proxyRequestEvent =
                ObjectMapperSingleton.objectToClassConvertValue(requestEvent, APIGatewayProxyRequestEvent.class);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        LogsUtil.getInstance(context);

        try {
            String authorization = proxyRequestEvent.getHeaders().get(AUTHORIZATION);

            JwtMiddleware.validateToken(authorization);

            LogsUtil.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(), EventFlowHandler.class.getName());

            final String objectToJsonPathParameters =
                    ObjectMapperSingleton.objectToJson(proxyRequestEvent.getPathParameters());

            final EventFlowRequestDTO flowRequestDTO = ObjectMapperSingleton
                    .jsonToClass(objectToJsonPathParameters, EventFlowRequestDTO.class);

            final String uuid = proxyRequestEvent.getQueryStringParameters().get(UUID);

            LogsUtil.info(
                    AppLogsEnum.INSERT_DATA.getMessageWithCode(),
                    uuid,
                    EventFlowRequestDTO.class.getSimpleName(),
                    ObjectMapperSingleton.objectToJson(flowRequestDTO)
            );

            final ResponseEventFlowDTO responseEventFlowDTO = getEventsFlowUseCase.invoke(flowRequestDTO);

            LogsUtil.info(
                    DATA_RESPONSE_REPOSITORY.getMessageWithCode(),
                    ObjectMapperSingleton.objectToJson(responseEventFlowDTO)
            );

            validateDataOfOut(responseEventFlowDTO);
            responseEventFlowDTO.setUuid(uuid);

            apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstant.SC_OK.getCodeHttp())
                    .setObjectBody(ResponseMapper
                            .buildDefault(HttpStatusConstant.SC_OK.getCodeHttp(), responseEventFlowDTO,
                                    AppLogsEnum.CHANNEL_FOUND.getMessage()))
                    .build();

            LogsUtil.info(
                    AppLogsEnum.RESPONSE_DATA_OK.getMessageWithCode(),
                    ObjectMapperSingleton.objectToJson(responseEventFlowDTO),
                    ObjectMapperSingleton.objectToJson(apiGatewayResponseDTO)
            );

        } catch (ApplicationException e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e, e.getStackTrace(),
                    ObjectMapperSingleton.objectToJson(e.getApiGatewayResponseDTO())
            );

            apiGatewayResponseDTO = e.getApiGatewayResponseDTO();
        }
        return apiGatewayResponseDTO;
    }

    @SneakyThrows
    private static void validateDataOfOut(ResponseEventFlowDTO responseEventFlowDTO) {

        if (Objects.isNull(responseEventFlowDTO.getId())) {

            throw new DataNotFoundException(ResponseMapper
                    .buildWithError(HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                            ERROR_DATA_NOT_FOUND.getMessage(),
                            DEFAULT_BOOLEAN_TRUE), ERROR_DATA_NOT_FOUND.getMessage());
        }
    }
}
