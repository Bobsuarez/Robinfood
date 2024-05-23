package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.dtos.request.APIGatewayProxyRequestEventDTO;
import com.robinfood.dtos.request.orderbill.OrderDTO;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.OrderElectronicBillingDTO;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecase.sendtosimba.ISendMessageToSimbaUseCase;
import com.robinfood.usecase.sendtosimba.SendMessageToSimbaUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.Arrays;
import java.util.Map;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.enums.AppLogsTraceEnum.ENTERED_DATA;
import static com.robinfood.enums.AppLogsTraceEnum.PROGRAM_START;
import static com.robinfood.enums.AppLogsTraceEnum.SEND_DATA_SIMBA;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class SendElectronicInvoiceToSimbaHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    private final ISendMessageToSimbaUseCase sendMessageToSimbaUseCase;

    public SendElectronicInvoiceToSimbaHandler(ISendMessageToSimbaUseCase sendMessageToSimbaUseCase) {
        this.sendMessageToSimbaUseCase = sendMessageToSimbaUseCase;
    }

    public SendElectronicInvoiceToSimbaHandler() {
        this.sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase();
    }

    private static TransactionRequestDTO parseRequestBody(String body) {
        return ObjectMapperSingleton.jsonToClass(body, TransactionRequestDTO.class);
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(Map<String, Object> requestEvent, Context context) {

        APIGatewayProxyRequestEventDTO proxyRequestEvent =
                ObjectMapperSingleton.objectToClassConvertValue(requestEvent, APIGatewayProxyRequestEventDTO.class);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        LogsUtil.getInstance(context);

        try {

            final String authorization = proxyRequestEvent.getHeaders().get(AUTHORIZATION);
            JwtMiddleware.validateToken(authorization);

            LogsUtil.info(PROGRAM_START.getMessageWithCode(), SendElectronicInvoiceToSimbaHandler.class.getName());

            final TransactionRequestDTO transactionRequestDTO = parseRequestBody(proxyRequestEvent.getBody());

            LogsUtil.info(
                    ENTERED_DATA.getMessageWithCode(),
                    transactionRequestDTO.getUuid(),
                    OrderDTO.class.getSimpleName(),
                    ObjectMapperSingleton.objectToJson(transactionRequestDTO)
            );

            final OrderElectronicBillingDTO responseSimba = sendMessageToSimbaUseCase.invoke(transactionRequestDTO);

            int statusCode = HttpStatusConstants.SC_CREATED.getCodeHttp();

            if (responseSimba.getStatusCode() == HttpStatusConstants.SC_INTERNAL_SERVER_ERROR.getCodeHttp()) {
                statusCode = HttpStatusConstants.SC_BAD_REQUEST.getCodeHttp();
            }

            return ApiGatewayResponseDTO.builder()
                    .setStatusCode(statusCode)
                    .setObjectBody(ResponseMapper
                            .buildDefault(statusCode,
                                    responseSimba,
                                    SEND_DATA_SIMBA.getMessage()
                            )
                    )
                    .build();

        } catch (ApplicationException e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e, Arrays.toString(e.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(e.getApiGatewayResponseDTO())
            );

            apiGatewayResponseDTO = e.getApiGatewayResponseDTO();
        }
        return apiGatewayResponseDTO;
    }
}
