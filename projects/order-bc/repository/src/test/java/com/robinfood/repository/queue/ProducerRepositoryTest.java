package com.robinfood.repository.queue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ProducerRepositoryTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private ProducerOrderRepository repository;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(repository, "ordersCreatedTopic", "ordersCreatedTopic");
        ReflectionTestUtils.setField(repository, "changeStatusTopic", "changeStatusTopic");

    }

    @Test
    void test_Send_Order_Created_Message_Returns_Successfully() {

        doNothing().when(jmsTemplate)
            .convertAndSend(nullable(String.class), nullable(String.class),any());

        repository.sendOrderCreatedMessage(new OrderCreatedQueueDTO());

        verify(jmsTemplate).setPubSubDomain(Boolean.TRUE);
        verify(jmsTemplate).convertAndSend(nullable(String.class), nullable(String.class),any());
    }

    @Test
    void test_Send_Order_Created_Message_Should_ThrowError_When_JmsException() {

        doThrow(new JmsException("test exception") {})
            .when(jmsTemplate).convertAndSend(nullable(String.class), any(String.class),any());

        repository.sendOrderCreatedMessage(new OrderCreatedQueueDTO());

        verify(jmsTemplate).setPubSubDomain(true);
        verify(jmsTemplate).convertAndSend(nullable(String.class), nullable(String.class),any());
    }

    @Test
    void test_Write_Change_Status_Message_Returns_Successfully() {
        doNothing().when(jmsTemplate)
            .convertAndSend(nullable(String.class), nullable(String.class));

        repository.sendChangeStatusMessage(new WriteChangeStatusDTO());

        verify(jmsTemplate).setPubSubDomain(Boolean.TRUE);
        verify(jmsTemplate).convertAndSend(nullable(String.class), nullable(String.class));
    }

    @Test
    void test_Write_Change_Status_Message_Should_ThrowError_When_JmsException() {
        doThrow(new JmsException("test exception") {})
            .when(jmsTemplate).convertAndSend(nullable(String.class), any(String.class));

        repository.sendChangeStatusMessage(new WriteChangeStatusDTO());

        verify(jmsTemplate).setPubSubDomain(true);
        verify(jmsTemplate).convertAndSend(nullable(String.class), nullable(String.class));
    }
}
