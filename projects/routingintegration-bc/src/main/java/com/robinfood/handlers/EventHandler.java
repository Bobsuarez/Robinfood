package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.createeventflow.request.EventRequestDTO;
import com.robinfood.dtos.createeventflow.response.FlowEventLogsResponseDTO;
import com.robinfood.dtos.request.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.createevent.CreateEventUseCase;
import com.robinfood.usecases.createevent.ICreateEventUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import com.robinfood.util.ValidatorFieldsUtil;

import java.util.Map;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.enums.AppLogsEnum.SAVE_INFORMATION_EVENT;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class EventHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    private final ICreateEventUseCase createEventUseCase;

    public EventHandler(ICreateEventUseCase createEventUseCase) {
        this.createEventUseCase = createEventUseCase;
    }

    public EventHandler() {
        this.createEventUseCase = new CreateEventUseCase();
    }

    private static EventRequestDTO parseRequestBody(String body) {
        return ObjectMapperSingleton.jsonToClass(body, EventRequestDTO.class);
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(Map<String, Object> requestEvent, Context context) {

        APIGatewayProxyRequestEvent proxyRequestEvent =
                ObjectMapperSingleton.objectToClassConvertValue(requestEvent, APIGatewayProxyRequestEvent.class);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        LogsUtil.getInstance(context);

        try {

            final String authorization = proxyRequestEvent.getHeaders().get(AUTHORIZATION);
            JwtMiddleware.validateToken(authorization);

            LogsUtil.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(), EventFlowHandler.class.getName());

            final EventRequestDTO eventRequestDTO = parseRequestBody(proxyRequestEvent.getBody());

            LogsUtil.info(
                    AppLogsEnum.INSERT_DATA.getMessageWithCode(),
                    eventRequestDTO.getUuid(),
                    EventRequestDTO.class.getSimpleName(),
                    ObjectMapperSingleton.objectToJson(eventRequestDTO)
            );

            ValidatorFieldsUtil.validateNullFields(eventRequestDTO);

            final FlowEventLogsResponseDTO flowEventLogsDTO = createEventUseCase.invoke(eventRequestDTO);

            LogsUtil.info(
                    SAVE_INFORMATION_EVENT.getMessageWithCode(),
                    eventRequestDTO.getUuid(),
                    eventRequestDTO
            );

            return ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstant.SC_CREATED.getCodeHttp())
                    .setObjectBody(ResponseMapper
                            .buildDefault(
                                    HttpStatusConstant.SC_CREATED.getCodeHttp(),
                                    flowEventLogsDTO,
                                    AppLogsEnum.EVENT_CREATE.getMessage()
                            )
                    )
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
