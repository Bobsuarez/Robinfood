package com.robinfood.app.usecases.paymentmethodproducer;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodProducerUseCaseTest {

    @Mock
    private IProducerRepository producerRepository;

    @InjectMocks
    private PaymentMethodProducerUseCase paymentMethodProducerUseCase;

    @Test
    void test_When_PaymentMethodProducer_Happy_Path() {
        doNothing().when(producerRepository).sendChangeStatusMessage(any(ChangeOrderStatusDTO.class));

        ChangeOrderStatusDTO changeOrderStatus = ChangeOrderStatusDTO.builder()
            .notes("prueba")
            .orderId(1L)
            .origin("test")
            .statusCode("SEND_TEST")
            .statusId(2L)
            .userId(1L)
            .build();

        paymentMethodProducerUseCase.invoke(changeOrderStatus);

        verify(producerRepository, times(1))
            .sendChangeStatusMessage(any(ChangeOrderStatusDTO.class));
    }
}
