package com.robinfood.ordereports_bc_muyapp.usecases.getresponsepaymentmethodbyorder;

import com.robinfood.ordereports_bc_muyapp.datamock.dto.OrderPaymentDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.PaymentMethodEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponsePaymentMethodDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.PaymentMethodEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.paymentmethod.IPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetResponsePaymentMethodByOrderUseCaseTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @Mock
    private OrderDetailOrderMapper orderDetailOrderMapper;

    @InjectMocks
    private GetResponsePaymentMethodByOrderUseCase getResponsePaymentMethodByOrderUseCase;

    @Test
    void test_ValidatedPaymentMethod_When_IsPresent_Should_OptionalPaymentMethodDTO_Return() {

        when(paymentMethodRepository.findById((short) 2)).thenReturn(
                Optional.of(PaymentMethodEntityMock.getDataDefault())
        );

        when(orderDetailOrderMapper.mapOrderPaymentMethodToResponseDTO(any(OrderPaymentDTO.class), any(
                PaymentMethodEntity.class)))
                .thenReturn(ResponsePaymentMethodDTO.builder()
                                    .id((short) 1)
                                    .build());

        Optional<ResponsePaymentMethodDTO> responseOptional =
                getResponsePaymentMethodByOrderUseCase.invoke(OrderPaymentDTOMock.getDataDefault());


        assertTrue(responseOptional.isPresent());
    }
}