package co.com.robinfood.queue.thread;

import co.com.robinfood.queue.Exceptions.ApplicationException;
import co.com.robinfood.queue.persistencia.dto.orderdetail.OrderDetailDTO;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;


@Slf4j
public class ThreadSendSQS implements Runnable {

    private final QueueMessagingTemplate messagingTemplate;
    private final List<OrderDetailDTO> orderDetailDTOS;

    private final String url;

    public ThreadSendSQS(QueueMessagingTemplate messagingTemplate, List<OrderDetailDTO> orderDetailDTOS, String url) {
        this.messagingTemplate = messagingTemplate;
        this.orderDetailDTOS = orderDetailDTOS;
        this.url = url;
    }

    @Override
    public void run() {
        orderDetailDTOS.forEach((data) -> sendOrderCreatedMessage(data, url));
    }

    private void sendOrderCreatedMessage(OrderDetailDTO orderDetailDTO, String queueName) {

        Message<OrderDetailDTO> orderDetailDTOMessage = MessageBuilder
                .withPayload(orderDetailDTO)
                .setHeader("message-group-id", "order-create-and-paid")
                .setHeader("message-deduplication-id", orderDetailDTO.getId().toString())
                .build();

        try {

            messagingTemplate.convertAndSend(
                    queueName,
                    orderDetailDTOMessage.getPayload(),
                    orderDetailDTOMessage.getHeaders()
            );

        } catch (Exception exception) {
            log.error("Not sent id : {} , exception : {}", orderDetailDTO.getId(), exception);
            throw new ApplicationException(201, "exception : " + exception.getMessage());
        }

        log.info(" ---> Message was sent successfully uuid : {} , id : {} ",
                orderDetailDTO.getOrderUuid(),
                orderDetailDTO.getId()
        );
    }
}
