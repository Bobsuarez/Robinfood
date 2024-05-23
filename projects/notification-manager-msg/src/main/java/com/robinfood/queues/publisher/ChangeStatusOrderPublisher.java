package com.robinfood.queues.publisher;

import com.robinfood.configs.JmsConnectionConfig;
import com.robinfood.dtos.request.RequestPublishEventDTO;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingletonUtil;

import java.util.concurrent.CompletableFuture;

import static com.robinfood.constants.APIConstants.DESTINATION;
import static com.robinfood.enums.AppLogsTraceEnum.MESSAGE_SENT_SUCCESSFULLY;
import static com.robinfood.enums.AppLogsTraceEnum.SEND_MESSAGE_AMQ;


public final class ChangeStatusOrderPublisher {

    private ChangeStatusOrderPublisher() {
        throw new IllegalStateException("Utility class");
    }

    public static void sendMessage(RequestPublishEventDTO requestPublishEventDTO) {

        LogsUtil.info(SEND_MESSAGE_AMQ.getMessage(),
                ObjectMapperSingletonUtil.objectToJson(requestPublishEventDTO),
                DESTINATION
        );

        JmsConnectionConfig connectionConfig = new JmsConnectionConfig();

        CompletableFuture<Void> sendingMessageToQueue = CompletableFuture.runAsync(() ->
                connectionConfig.jmsTemplate().convertAndSend(
                        DESTINATION,
                        requestPublishEventDTO
                ));

        sendingMessageToQueue.join();

        LogsUtil.info(MESSAGE_SENT_SUCCESSFULLY.getMessage(),
                ObjectMapperSingletonUtil.objectToJson(requestPublishEventDTO),
                DESTINATION
        );

    }
}
