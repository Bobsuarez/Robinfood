package com.robinfood.changestatusbc.repositories.queue;

import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static com.robinfood.changestatusbc.enums.AppTraceEnum.ERROR_JMS_EXCEPTION;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.SENDING_CHANGE_STATUS_MESSAGE;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.SUCCESSFULLY_CHANGE_STATUS_MESSAGE;
import static com.robinfood.changestatusbc.utilities.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class ProducerOrderRepository implements IProducerOrderRepository {

    private final JmsTemplate jmsTemplate;

    @Value("${activemq.orders-status-changed.topic}")
    private String changeStatusTopic;

    @Value("${activemq.orders-notifications-change.queue}")
    private String ordersNotificationChange;

    public ProducerOrderRepository(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendChangeStatusMessage(WriteChangeStatusDTO writeChangeStatusDTO) {

        try {
            log.info(
                    SENDING_CHANGE_STATUS_MESSAGE.getMessageWithCode(),
                    objectToJson(writeChangeStatusDTO),
                    changeStatusTopic
            );

            String changeStatusMessage = objectToJson(writeChangeStatusDTO);

            sendMessageToMQ(changeStatusMessage, changeStatusTopic, Boolean.TRUE);
            sendMessageToMQ(changeStatusMessage, ordersNotificationChange, Boolean.FALSE);


        } catch (JmsException exception) {
            log.error(
                    ERROR_JMS_EXCEPTION.getMessage(),
                    objectToJson(writeChangeStatusDTO),
                    changeStatusTopic,
                    exception
            );
        }
    }

    private void sendMessageToMQ(String changeStatusMessage, String destinationName, boolean isTopic) {

        jmsTemplate.setPubSubDomain(isTopic);
        jmsTemplate.convertAndSend(
                destinationName,
                changeStatusMessage
        );

        log.info(SUCCESSFULLY_CHANGE_STATUS_MESSAGE.getMessageWithCode(),
                objectToJson(changeStatusMessage),
                destinationName);

    }
}
