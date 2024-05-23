package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.createsubscribereventhistorylogs.request.SubscriberEventHistoryLogsRequestDTO;
import com.robinfood.dtos.createsubscribereventhistorylogs.response.SubscriberEventHistoryLogsResponseDTO;
import com.robinfood.dtos.request.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.createsubscribereventhistorylogs.CreateSubscriberEventHistoryLogsUseCase;
import com.robinfood.usecases.createsubscribereventhistorylogs.ICreateSubscriberEventHistoryLogsUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import com.robinfood.util.ValidatorFieldsUtil;

import java.util.Map;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.enums.AppLogsEnum.SAVE_INFORMATION_EVENT_HISTORY;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class SubscriberEventHistoryLogsHandler implements
        RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    private final ICreateSubscriberEventHistoryLogsUseCase createSubscriberEventHistoryLogsUseCase;

    public SubscriberEventHistoryLogsHandler(
            ICreateSubscriberEventHistoryLogsUseCase createSubscriberEventHistoryLogsUseCase
    ) {
        this.createSubscriberEventHistoryLogsUseCase = createSubscriberEventHistoryLogsUseCase;
    }

    public SubscriberEventHistoryLogsHandler() {
        this.createSubscriberEventHistoryLogsUseCase = new CreateSubscriberEventHistoryLogsUseCase();
    }

    private static SubscriberEventHistoryLogsRequestDTO parseRequestBody(String body) {
        return ObjectMapperSingleton.jsonToClass(body, SubscriberEventHistoryLogsRequestDTO.class);
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

            LogsUtil.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(),
                    SubscriberEventHistoryLogsHandler.class.getName()
            );

            final SubscriberEventHistoryLogsRequestDTO requestDTO = parseRequestBody(proxyRequestEvent.getBody());

            LogsUtil.info(
                    AppLogsEnum.INSERT_DATA.getMessageWithCode(),
                    requestDTO.getUuid(),
                    SubscriberEventHistoryLogsRequestDTO.class.getSimpleName(),
                    ObjectMapperSingleton.objectToJson(requestDTO)
            );

            ValidatorFieldsUtil.validateNullFields(requestDTO);

            final SubscriberEventHistoryLogsResponseDTO responseDTO =
                    createSubscriberEventHistoryLogsUseCase.invoke(requestDTO);

            LogsUtil.info(
                    SAVE_INFORMATION_EVENT_HISTORY.getMessageWithCode(),
                    requestDTO.getUuid(),
                    requestDTO
            );

            return ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstant.SC_CREATED.getCodeHttp())
                    .setObjectBody(ResponseMapper
                            .buildDefault(
                                    HttpStatusConstant.SC_CREATED.getCodeHttp(), responseDTO,
                                    AppLogsEnum.EVENT_HISTORY_CREATE.getMessage()
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
