package com.robinfood.repository.queue.activemq;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.enums.logs.OrderErrorLogEnum;
import com.robinfood.core.enums.logs.OrderLogEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of IProducerRepository
 */
@Slf4j
@Component
public class ProducerRepository implements IProducerRepository {

    private final JmsTemplate jmsTemplate;

    @Value("${activemq.orders-response.topic}")
    private String responseTopic;

    @Value("${activemq.orders-change-status.queue}")
    private String changeStatusQueue;

    public ProducerRepository(
            @NotNull @Qualifier("jmsTemplateOrders") final JmsTemplate jmsTemplate
    ) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrderToCreateMessage(
            OrderToCreateDTO orderToCreateDTO, String messageFrom,
            String messageCountry
    ) {

        try {
            log.info(OrderLogEnum.ORDER_SENDING_AMQ_QUEUE_ORDER_TO_CREATE_PLATFORMS.getMessage(),
                    objectToJson(orderToCreateDTO), responseTopic);

            jmsTemplate.setPubSubDomain(false);
            jmsTemplate.convertAndSend(responseTopic, orderToCreateDTO, (Message m) -> {
                m.setStringProperty("from", messageFrom);
                m.setStringProperty("country", messageCountry);
                return m;
            });

            log.info(OrderLogEnum.ORDER_SUCCESSFULLY_AMQ_QUEUE_ORDER_TO_CREATE_PLATFORMS.getMessage(),
                    objectToJson(orderToCreateDTO), responseTopic);

        } catch (JmsException e) {

            log.error(OrderErrorLogEnum.ERROR_ORDER_TRANSACTION_SEND_ACTIVEMQ_ORDER_TO_CREATE.getMessage(),
                    objectToJson(orderToCreateDTO), responseTopic, e);
            throw e;
        }
    }

    @Override
    public void sendChangeStatusMessage(ChangeOrderStatusDTO changeOrderStatusDTO) {

        final String messageToSend = getMessageSendingWhenOrderUidExists(changeOrderStatusDTO.getOrderUid());
        final String messageToSuccessful = getMessageSuccessfullyWhenOrderUidExists(changeOrderStatusDTO.getOrderUid());

        try {
            log.info(messageToSend, objectToJson(changeOrderStatusDTO), changeStatusQueue);

            jmsTemplate.setPubSubDomain(false);
            jmsTemplate.convertAndSend(changeStatusQueue, changeOrderStatusDTO);

            log.info(messageToSuccessful, objectToJson(changeOrderStatusDTO), changeStatusQueue);

        } catch (JmsException exception) {
            log.error(OrderErrorLogEnum.ERROR_ORDER_TRANSACTION_SEND_ACTIVEMQ_CHANGE_STATUS.getMessage(),
                    objectToJson(changeOrderStatusDTO),
                    changeStatusQueue, exception
            );
        }
    }

    private String getMessageSendingWhenOrderUidExists(final Long orderUid) {

        if (Objects.isNull(orderUid)) {
            return OrderLogEnum.ORDER_SENDING_AMQ_CHANGE_STATUS_DISCARDED_ORDER.getMessage();
        }
        return OrderLogEnum.ORDER_SENDING_AMQ_CHANGE_STATUS_PAID_ORDER.getMessage();
    }

    private String getMessageSuccessfullyWhenOrderUidExists(final Long orderUid) {

        if (Objects.isNull(orderUid)) {
            return OrderLogEnum.ORDER_SENDING_AMQ_CHANGE_STATUS_DISCARDED_ORDER_SUCCESSFULLY.getMessage();
        }
        return OrderLogEnum.ORDER_SENDING_AMQ_CHANGE_STATUS_PAID_ORDER_SUCCESSFULLY.getMessage();
    }
}
