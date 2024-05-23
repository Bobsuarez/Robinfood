package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.constants.ThirdPartyConstants;
import com.robinfood.dtos.request.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.sendordertosimba.request.OrderDTO;
import com.robinfood.dtos.sendordertosimba.request.ThirdPartyDTO;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.dtos.sendordertosimba.response.TransactionResponseDTO;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.DataNotFoundException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.processconnectortosimba.IProcessConnectorToSimbaUseCase;
import com.robinfood.usecases.processconnectortosimba.ProcessConnectorToSimbaUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import com.robinfood.util.ValidatorFieldsUtil;

import java.util.Map;
import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.THIRD_PARTY;
import static com.robinfood.constants.TokenConstants.AUTHORIZATION;
import static com.robinfood.enums.AppLogsTraceEnum.DATA_ASSIGN_THIRD_PARTY;
import static com.robinfood.enums.AppLogsTraceEnum.SEND_INFORMATION_SIMBA_OK;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_DATA_NOT_FOUND_COMPLEMENT;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_VALIDATE_THIRD_PARTY;
import static com.robinfood.util.ValidatorFieldsUtil.validateOrderDTO;

public class SendOrderToSimbaHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponseDTO> {

    private final IProcessConnectorToSimbaUseCase sendOrderToSimbaUseCase;

    public SendOrderToSimbaHandler(IProcessConnectorToSimbaUseCase sendOrderToSimbaUseCase) {
        this.sendOrderToSimbaUseCase = sendOrderToSimbaUseCase;
    }

    public SendOrderToSimbaHandler() {
        this.sendOrderToSimbaUseCase = new ProcessConnectorToSimbaUseCase();
    }

    private static TransactionRequestDTO parseRequestBody(String body) {
        return ObjectMapperSingleton.jsonToClass(body, TransactionRequestDTO.class);
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(Map<String, Object> requestEvent, Context context) {

        final APIGatewayProxyRequestEvent proxyRequestEvent =
                ObjectMapperSingleton.objectToClassConvertValue(requestEvent, APIGatewayProxyRequestEvent.class);

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        LogsUtil.getInstance(context);

        try {

            final String tokenAuthorization = proxyRequestEvent.getHeaders().get(AUTHORIZATION);

            JwtMiddleware.validateToken(tokenAuthorization);

            final TransactionRequestDTO transactionRequestDTO = parseRequestBody(proxyRequestEvent.getBody());

            LogsUtil.info(
                    AppLogsTraceEnum.PROGRAM_START.getMessageWithCode(),
                    SendOrderToSimbaHandler.class.getName(),
                    ObjectMapperSingleton.objectToJson(transactionRequestDTO)
            );

            final OrderDTO orderDTO = validateOrderDTO(transactionRequestDTO);

            LogsUtil.info(
                    AppLogsTraceEnum.ENTERED_DATA.getMessageWithCode(),
                    ObjectMapperSingleton.objectToJson(orderDTO.getId()),
                    ObjectMapperSingleton.objectToJson(orderDTO.getThirdParty())
            );

            assignThirdParty(orderDTO);

            final TransactionResponseDTO transactionResponseDTO =
                    sendOrderToSimbaUseCase.invoke(transactionRequestDTO, tokenAuthorization);

            LogsUtil.info(
                    SEND_INFORMATION_SIMBA_OK.getMessageWithCode(),
                    ObjectMapperSingleton.objectToJson(orderDTO.getId()),
                    ObjectMapperSingleton.objectToJson(transactionResponseDTO)
            );

            return ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatusConstants.SC_ACCEPTED.getCodeHttp())
                    .setObjectBody(ResponseMapper
                            .buildDefault(
                                    HttpStatusConstants.SC_ACCEPTED.getCodeHttp(),
                                    transactionResponseDTO,
                                    AppLogsTraceEnum.ORDER_SENT_SIMBA.getMessage()
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

    private static void assignThirdParty(OrderDTO orderDTO) {

        try {

            if (Objects.isNull(orderDTO.getThirdParty())) {
                throw new DataNotFoundException(
                        ResponseMapper
                                .buildWithError(HttpStatusConstants.SC_NOT_FOUND.getCodeHttp(),
                                        ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(THIRD_PARTY),
                                        DEFAULT_BOOLEAN_TRUE),
                        ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(THIRD_PARTY));
            }

            LogsUtil.info(DATA_ASSIGN_THIRD_PARTY.getMessage(),
                    ObjectMapperSingleton.objectToJson(orderDTO.getThirdParty())
            );

            ValidatorFieldsUtil.validateNullFields(orderDTO.getThirdParty(),
                    "documentNumber", "documentType", "email", "fullName");


        } catch (ApplicationException e) {

            LogsUtil.error(
                    ERROR_PROCESS_VALIDATE_THIRD_PARTY.getMessageWithCode(), e.getMessage()
            );

            orderDTO.setThirdParty(ThirdPartyDTO.builder()
                    .documentNumber(ThirdPartyConstants.THIRD_PARTY_DOCUMENT_NUMBER)
                    .fullName(ThirdPartyConstants.THIRD_PARTY_FULL_NAME)
                    .email(ThirdPartyConstants.THIRD_PARTY_EMAIL)
                    .documentType(Integer.parseInt(ThirdPartyConstants.THIRD_PARTY_DOCUMENT_TYPE))
                    .build());
        }
    }
}


