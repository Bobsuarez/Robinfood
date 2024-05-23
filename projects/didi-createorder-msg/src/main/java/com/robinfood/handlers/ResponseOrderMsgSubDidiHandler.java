package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.enums.ErrorLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.DataNotFoundException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.usecases.processhandler.IProcessHandlerUseCase;
import com.robinfood.usecases.processhandler.ProcessHandlerUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingletonUtil;
import com.robinfood.util.ValidatorFieldsUtil;

import java.util.Map;

import static com.robinfood.constants.GeneralConstants.MESSAGE_COUNTRY_OUT;
import static com.robinfood.constants.GeneralConstants.MESSAGE_FROM_OUT;
import static com.robinfood.enums.AppLogsTraceEnum.MESSAGE_BODY_SQS;
import static com.robinfood.enums.AppLogsTraceEnum.PROGRAM_START;
import static com.robinfood.enums.AppLogsTraceEnum.SEND_MESSAGE_SUCCESSFULLY_OU;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class ResponseOrderMsgSubDidiHandler implements RequestHandler<SQSEvent, Void> {

    private final IProcessHandlerUseCase processHandlerUseCase;

    public ResponseOrderMsgSubDidiHandler(IProcessHandlerUseCase processHandlerUseCase) {
        this.processHandlerUseCase = processHandlerUseCase;
    }

    public ResponseOrderMsgSubDidiHandler() {
        this.processHandlerUseCase = new ProcessHandlerUseCase();
    }

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {


        LogsUtil.getInstance(context);

        LogsUtil.info(PROGRAM_START.getMessage(), ObjectMapperSingletonUtil.objectToJson(sqsEvent));

        try {

            final SQSEvent.SQSMessage message = processMessage(sqsEvent);

            final String bodyMessage = message.getBody();

            final Map<String, SQSEvent.MessageAttribute> messageAttribute = message.getMessageAttributes();

            LogsUtil.info(MESSAGE_BODY_SQS.getMessage(), message,
                    ObjectMapperSingletonUtil.objectToJson(messageAttribute)
            );

            final OrderToCreateDTO orderToCreateDTO = ObjectMapperSingletonUtil
                    .jsonToClass(bodyMessage, OrderToCreateDTO.class);

            ValidatorFieldsUtil.validateNullFields(orderToCreateDTO,
                    "company", "device", "orders", "origin", "paid", "paymentMethods", "user", "uuid");

            Map<String , String> dataConverter =
                    ValidatorFieldsUtil
                            .validateAttributesSQS(messageAttribute, MESSAGE_FROM_OUT, MESSAGE_COUNTRY_OUT);

            processHandlerUseCase.invoke(orderToCreateDTO, dataConverter);

            LogsUtil.info(SEND_MESSAGE_SUCCESSFULLY_OU.getMessage(),
                    ObjectMapperSingletonUtil.objectToJson(orderToCreateDTO));

        } catch (ApplicationException e) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e, e.getStackTrace(),
                    ObjectMapperSingletonUtil.objectToJson(e.getApiGatewayResponseDTO())
            );

        }
        return null;
    }


    private SQSEvent.SQSMessage processMessage(SQSEvent sqsEvent) {

        return sqsEvent.getRecords()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new DataNotFoundException(
                                ResponseMapper.buildWithError(HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                                        ErrorLogsEnum.ERROR_DATA_NOT_FOUND.getMessageWithCode(), Boolean.TRUE),
                                ErrorLogsEnum.ERROR_DATA_NOT_FOUND.getMessageWithCode()));
    }
}
