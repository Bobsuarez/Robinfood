package com.robinfood.orderorlocalserver.repositories.sqs;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.mocks.dtos.OrderDetailPrintDTOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class OrderSqsRepositoryTest {

    @Mock
    private QueueMessagingTemplate queueMessagingTemplate;

    @InjectMocks
    OrderSqsRepository orderSqsRepository;


    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(orderSqsRepository, "queueName", "queueName");
    }

    OrderDetailDTO orderDetailDTO = OrderDetailPrintDTOMock.getOrderDetailDTOS().get(0);


    @Test
    void test_send_order_sqs_repository_ok() {

        doNothing().when(queueMessagingTemplate)
                .convertAndSend(
                        anyString(),
                        any(OrderDetailDTO.class),
                        any(MessageHeaders.class)
                );

        orderSqsRepository.sendOrderCreatedMessage(orderDetailDTO);

        verify(queueMessagingTemplate).convertAndSend(
                anyString(),
                any(OrderDetailDTO.class),
                any(MessageHeaders.class)
        );

    }

}