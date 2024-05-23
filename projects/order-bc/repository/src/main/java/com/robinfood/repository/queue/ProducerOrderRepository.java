package com.robinfood.repository.queue;

import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

import static com.robinfood.core.enums.AppOrderBcTraceEnum.ERROR_JMS_EXCEPTION;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.SENDING_CHANGE_STATUS_MESSAGE;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.SUCCESSFULLY_CHANGE_STATUS_MESSAGE;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

@Repository
@Slf4j
public class ProducerOrderRepository implements IProducerOrderRepository {

    private final JmsTemplate jmsTemplate;

    @Value("${activemq.orders-created.topic}")
    private String ordersCreatedTopic;

    @Value("${activemq.orders-status-changed.topic}")
    private String changeStatusTopic;

    @Value("${activemq.orders-created-delay}")
    private Long delayMessageQueue;

    public ProducerOrderRepository(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrderCreatedMessage(OrderCreatedQueueDTO orderCreatedQueueDTO) {
        try {
            log.info("Sending order created message {} to the ActiveMQ topic {}.",
                    objectToJson(orderCreatedQueueDTO), ordersCreatedTopic
            );

            String orderCreatedMessage = objectToJson(orderCreatedQueueDTO);

            log.info("Order to publish {}", orderCreatedMessage);

            jmsTemplate.setPubSubDomain(Boolean.TRUE);
            jmsTemplate.convertAndSend(ordersCreatedTopic, orderCreatedMessage, message -> {
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delayMessageQueue);
                return message;
            });

            log.info(
                    "Message {} sent successfully to ActiveMQ queue {}",
                    objectToJson(orderCreatedQueueDTO),
                    ordersCreatedTopic
            );
        } catch (JmsException exception) {
            log.error(
                    "Error while sending message {} to ActiveMQ queue {}",
                    objectToJson(orderCreatedQueueDTO),
                    ordersCreatedTopic,
                    exception
            );
        }
    }

    /**
     * Sends order created data to activemq topic
     *
     * @param writeChangeStatusDTO data to be sent corresponding to change status order
     */
    @Override
    public void sendChangeStatusMessage(WriteChangeStatusDTO writeChangeStatusDTO) {

        try {
            log.info(
                    SENDING_CHANGE_STATUS_MESSAGE.getMessageWithCode(),
                    objectToJson(writeChangeStatusDTO),
                    changeStatusTopic
            );

            String changeStatusMessage = objectToJson(writeChangeStatusDTO);

            jmsTemplate.setPubSubDomain(Boolean.TRUE);
            jmsTemplate.convertAndSend(
                    changeStatusTopic,
                    changeStatusMessage
            );

            log.info(SUCCESSFULLY_CHANGE_STATUS_MESSAGE.getMessageWithCode(),
                    objectToJson(writeChangeStatusDTO),
                    changeStatusTopic);

        } catch (JmsException exception) {
            log.error(
                    ERROR_JMS_EXCEPTION.getMessage(),
                    objectToJson(writeChangeStatusDTO),
                    changeStatusTopic,
                    exception
            );
        }
    }
}
