package com.robinfood.app.usecases.messageproducer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessageProducerUseCaseTest {

    @Mock
    private IProducerRepository producerRepository;

    @InjectMocks
    private MessageProducerUseCase messageProducerUseCase;

    @Test
    void test_MessageProducerUseCaseInvoke_Should_Void_When_ReceivedValidParams() {

        doNothing().when(producerRepository)
            .sendOrderToCreateMessage(any(OrderToCreateDTO.class), anyString(), anyString());

        OrderToCreateDTO orderToCreateDTO = new OrderToCreateDTO();
        orderToCreateDTO.setStatus("status");
        orderToCreateDTO.setMessage("message");
        orderToCreateDTO.setOrderId("123");
        orderToCreateDTO.setRejectType("rejectType");
        orderToCreateDTO.setSkuRejected(Collections.singletonList("1234"));
        messageProducerUseCase.invoke(orderToCreateDTO, "transformIntegration", "Mexico");

        verify(producerRepository, times(1)).sendOrderToCreateMessage(any(OrderToCreateDTO.class), anyString(),
            anyString());

    }
}
