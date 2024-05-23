package com.robinfood.usecases.processhandler;

import com.amazonaws.services.lambda.runtime.events.ActiveMQEvent;
import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.exceptions.ActiveMQEventNullException;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ChangeStatusMapper;
import com.robinfood.usecases.subscriberchangestatus.SubscriberChangeStatusUseCase;
import com.robinfood.utils.FormatStringEventActivemqUtil;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;
import com.robinfood.utils.ValidatorFieldsUtil;
import io.jsonwebtoken.impl.TextCodec;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.robinfood.constants.Constants.DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_URL_NOT_FOUND;

public class ProcessHandlerUseCase implements IProcessHandlerUseCase {

    private final SubscriberChangeStatusUseCase subscriberChangeStatusUseCase;

    public ProcessHandlerUseCase(SubscriberChangeStatusUseCase subscriberChangeStatusUseCase) {
        this.subscriberChangeStatusUseCase = subscriberChangeStatusUseCase;
    }

    public ProcessHandlerUseCase() {
        subscriberChangeStatusUseCase = new SubscriberChangeStatusUseCase();
    }

    @Override
    public void invoke(ActiveMQEvent activeMQEvent) {

        try {

            if (Objects.isNull(activeMQEvent)) {
                throw new ActiveMQEventNullException(
                        ERROR_URL_NOT_FOUND.getMessageWithCodeWithComplement("activeMQEvent"));
            }

            final ActiveMQEvent.ActiveMQMessage activeMQMessage = activeMQEvent.getMessages()
                    .get(DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT);

            byte[] decodeBase64 = TextCodec.BASE64URL.decode(activeMQMessage.getData());

            final String dataEntry = new String(decodeBase64, StandardCharsets.UTF_8);

            LogsUtil.info(AppLogsTraceEnum.EXECUTE_SUBSCRIBER_CHANGE_STATUS_HANDLER.getMessage(),
                    ObjectMapperSingleton.objectToJson(dataEntry));

            final String event = FormatStringEventActivemqUtil.execute(dataEntry);

            final ChangeStatusDTO changeStatusDTO = ChangeStatusMapper.eventStringToChangeStatusDto(event);

            ValidatorFieldsUtil
                    .validateNullFields(changeStatusDTO,
                            "channelId", "orderId", "orderUuid",
                            "statusCode", "transactionId", "transactionUuid");

            subscriberChangeStatusUseCase.invoke(changeStatusDTO, activeMQMessage.getMessageID());

        } catch (ApplicationException exception) {

            LogsUtil.error(ERROR_APP_EXCEPTION.getMessageWithCode(), exception, exception.getStackTrace(),
                    ObjectMapperSingleton.objectToJson(exception.getApiGatewayResponseDTO()));
        }
    }
}
