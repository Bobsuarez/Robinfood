package com.robinfood.app.usecases.getlistpaymentmethods;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.app.datamocks.entity.PaymentMethodEntityMock;
import com.robinfood.core.dtos.PaymentMethodEntityDTO;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetListPaymentMethodsUseCaseTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GetListPaymentMethodsUseCase getListPaymentMethods;

    @Test
    void test_GetDataResponse_Ok_When_FindAll_PaymentMethods() {

        when(paymentMethodRepository.findAll())
                .thenReturn(List.of(PaymentMethodEntityMock.getDataDefault()));

        when(objectMapper.convertValue(PaymentMethodEntity.class, PaymentMethodEntityDTO.class))
                .thenReturn(PaymentMethodEntityDTO.builder().build());

        List<PaymentMethodEntityDTO> responseInvoke = getListPaymentMethods.invoke();
        assertNotNull(responseInvoke);
    }
}
