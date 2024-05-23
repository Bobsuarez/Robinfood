package com.robinfood.app.usecases.getresponsepaymentmethodbyorder;

import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentMethodDTO;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Test of GetResponsePaymentMethodByOrderUseCase
 */
@ExtendWith(MockitoExtension.class)
public class GetResponsePaymentMethodByOrderUseCaseTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @InjectMocks
    private GetResponsePaymentMethodByOrderUseCase useCase;

    @Test
    void testGetResponsePaymentMethodByOrderWithResultShouldBeOk() {

        when(paymentMethodRepository.findById(123L)).thenReturn(
            Optional.of(getPaymentMethodEntity())
        );

        Optional<ResponsePaymentMethodDTO> responseOptional = useCase.invoke(getOrderPaymentDTO());
        assertTrue(responseOptional.isPresent());

        ResponsePaymentMethodDTO response = responseOptional.get();

        assertThat(response.getId(), is(equalTo(123L)));
        assertThat(response.getName(), is(equalTo("cash")));
        assertThat(response.getValue(), is(equalTo(1000.0)));
    }

    @Test
    void testGetResponsePaymentMethodByOrderWithoutResultShouldBeError() {
        when(paymentMethodRepository.findById(123L)).thenReturn(
            Optional.empty()
        );

        Optional<ResponsePaymentMethodDTO> responseOptional = useCase.invoke(getOrderPaymentDTO());
        assertFalse(responseOptional.isPresent());
    }

    private OrderPaymentDTO getOrderPaymentDTO() {
        return new OrderPaymentDTO(
            null,
            0.0,
            1L,
            12L,
            1L,
            123L,
            1000.0,
            0.0,
            1000.0
        );
    }

    private PaymentMethodEntity getPaymentMethodEntity() {
        return new PaymentMethodEntity(
            123L,
            1,
            1,
            1,
            "cash",
            "cash",
            "cash",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }
}
