package com.robinfood.orderorlocalserver.repositories.sqs;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderSqsRepository implements IOrderSqsRepository {

    @Value("${cloud.aws.end-point.url}")
    private String queueName;

    private final QueueMessagingTemplate messagingTemplate;

    public OrderSqsRepository(QueueMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendOrderCreatedMessage(OrderDetailDTO orderDetailDTO) {

        log.info("Starting repository to send a message to SQS. " +
                "This is the order detail to send {}",orderDetailDTO);

        Message<OrderDetailDTO> orderDetailDTOMessage = MessageBuilder
                .withPayload(orderDetailDTO)
                .setHeader("message-group-id", "order-create-and-paid")
                .setHeader("message-deduplication-id", orderDetailDTO.getId().toString())
                .build();

        log.info("Formed message ready to send {}",orderDetailDTOMessage);

        messagingTemplate.convertAndSend(
                queueName,
                orderDetailDTOMessage.getPayload(),
                orderDetailDTOMessage.getHeaders()
        );

        log.info("Message was sent successfully.");
    }
}
