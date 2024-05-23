package com.robinfood.orderorlocalserver.consumers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.orderorlocalserver.dtos.orderqueue.OrderCreatedQueueDTO;
import com.robinfood.orderorlocalserver.usecases.processordercreatedfromconsumer.IProcessOrderCreatedFromConsumerUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.robinfood.orderorlocalserver.enums.MessageLogEnum.MESSAGE_FAILED_WHILE_SENDING_TO_BE_PRINTED;
import static com.robinfood.orderorlocalserver.enums.MessageLogEnum.MESSAGE_RECEIVED_FROM_CREATED_ORDERS;

@Component
@Slf4j
public class OrderCreatedConsumer {

    @Value("${activemq.orders-created.topic}")
    private String activeMqQueue;

    private final IProcessOrderCreatedFromConsumerUseCase processOrderCreatedFromConsumerUseCase;

    public OrderCreatedConsumer( IProcessOrderCreatedFromConsumerUseCase processOrderCreatedFromConsumerUseCase) {
        this.processOrderCreatedFromConsumerUseCase = processOrderCreatedFromConsumerUseCase;
    }

    @JmsListener(
            destination = "${activemq.orders-created.topic}",
            containerFactory = "jmsListenerContainerFactoryOrdersQueue"
    )
    public void processMessageConsumer(String orderCreatedQueueRequest) {

        try {

            log.info("{} Queue: {} - Message: {}",
                    MESSAGE_RECEIVED_FROM_CREATED_ORDERS.getMessage(), activeMqQueue, orderCreatedQueueRequest);

            final ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            OrderCreatedQueueDTO orderCreatedQueueDTO = mapper.readValue(
                    orderCreatedQueueRequest,
                    OrderCreatedQueueDTO.class
            );

            processOrderCreatedFromConsumerUseCase.invoke(orderCreatedQueueDTO);

            log.info("Consumer finished");

        } catch (Exception exception) {
            log.error("{} Consumer finished. {}", MESSAGE_FAILED_WHILE_SENDING_TO_BE_PRINTED.getMessage(), exception);
        }
    }
}
