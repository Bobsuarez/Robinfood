package com.robinfood.queues.publisher;

import com.robinfood.configs.JmsConnectionConfig;
import com.robinfood.dtos.request.RequestChangeStateDTO;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingletonUtil;

import static com.robinfood.constants.APIConstants.DESTINATION;
import static com.robinfood.enums.AppLogsTraceEnum.MESSAGE_SENT_SUCCESSFULLY;
import static com.robinfood.enums.AppLogsTraceEnum.SEND_MESSAGE_AMQ;


public final class ChangeStatusOrderPublisher {

    private ChangeStatusOrderPublisher() {
        throw new IllegalStateException("Utility class");
    }

    public static void sendMessage(RequestChangeStateDTO requestChangeStateDTO) {

        JmsConnectionConfig connectionConfig = new JmsConnectionConfig();

        LogsUtil.info(SEND_MESSAGE_AMQ.getMessage(),
                ObjectMapperSingletonUtil.objectToJson(requestChangeStateDTO),
                DESTINATION
        );

        connectionConfig.jmsTemplate().convertAndSend(
                DESTINATION,
                requestChangeStateDTO
        );

        LogsUtil.info(MESSAGE_SENT_SUCCESSFULLY.getMessage(),
                ObjectMapperSingletonUtil.objectToJson(requestChangeStateDTO),
                DESTINATION
        );

    }
}
