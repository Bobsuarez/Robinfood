package com.robinfood.repository.queue.activemq;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.robinfood.core.dtos.ordertocreatedto.MessageDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ProducerRepositoryTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private ProducerRepository producerRepository;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(producerRepository, "responseTopic", "responseTopic");
        ReflectionTestUtils.setField(producerRepository, "changeStatusQueue", "changeStatusQueue");
    }

    @Test
    void test_Send_Order_To_Create_Message_Returns_Successfully() {

        doNothing().when(jmsTemplate)
            .convertAndSend(nullable(String.class), any(MessageDTO.class),
                any(MessagePostProcessor.class));

        producerRepository.sendOrderToCreateMessage(getOrderToCreateDTO(), "transformIntegration", "Mexico");

        verify(jmsTemplate).convertAndSend(nullable(String.class), any(MessageDTO.class),
                any(MessagePostProcessor.class));
    }

    @Test
    void test_Send_Message_Should_ThrowError_When_JmsException() {

        doThrow(new JmsException("test exception") {
        }).when(jmsTemplate).convertAndSend(nullable(String.class), any(MessageDTO.class),
            any(MessagePostProcessor.class));

        assertThrows(JmsException.class,
            () -> producerRepository.sendOrderToCreateMessage(getOrderToCreateDTO(), "transformIntegration", "Mexico"));
    }

    @Test
    void test_Send_Change_Status_Message_Should_ThrowError_When_JmsException() {

        doThrow(new JmsException("test exception") {
        }).when(jmsTemplate).convertAndSend(nullable(String.class), any(ChangeOrderStatusDTO.class));

        producerRepository.sendChangeStatusMessage(new ChangeOrderStatusDTO());

    }

    @Test
    void test_Send_Change_Status_Message_Returns_Successfully() {

        doNothing().when(jmsTemplate)
            .convertAndSend(nullable(String.class), any(ChangeOrderStatusDTO.class));

        producerRepository.sendChangeStatusMessage(getChangeOrderStatusDTO());

        verify(jmsTemplate).convertAndSend(nullable(String.class), any(ChangeOrderStatusDTO.class));
    }

    @Test
    void test_Send_Change_Status_Message_Returns_Successfully_With_Ids() {

        doNothing().when(jmsTemplate)
                .convertAndSend(nullable(String.class), any(ChangeOrderStatusDTO.class));

        ChangeOrderStatusDTO changeOrderStatusDTO = getChangeOrderStatusDTO();
        changeOrderStatusDTO.setOrderUid(123L);

        producerRepository.sendChangeStatusMessage(changeOrderStatusDTO);

        verify(jmsTemplate).convertAndSend(nullable(String.class), any(ChangeOrderStatusDTO.class));
    }

    private OrderToCreateDTO getOrderToCreateDTO() {
        OrderToCreateDTO orderToCreateDTO = new OrderToCreateDTO();
        orderToCreateDTO.setStatus("status");
        orderToCreateDTO.setMessage("message");
        orderToCreateDTO.setDescription("description");
        orderToCreateDTO.setOrderId("123");
        orderToCreateDTO.setStoreId("456");
        orderToCreateDTO.setOrderTotal(BigDecimal.ONE);
        orderToCreateDTO.setRejectType("rejectType");
        orderToCreateDTO.setSkuRejected(new ArrayList<>());

        return orderToCreateDTO;
    }

    private ChangeOrderStatusDTO getChangeOrderStatusDTO(){
       return ChangeOrderStatusDTO.builder()
            .notes("Test")
            .orderId(1L)
            .origin("origin test")
            .statusCode("TEST_SEND_MESSAGE")
            .statusId(2L)
            .userId(1234L)
            .build();
    }
}
