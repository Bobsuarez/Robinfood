package com.robinfood.app.usecases.createpayments;

import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import com.robinfood.core.entities.PaymentEntity;
import com.robinfood.repository.payment.IPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CreatePaymentsUseCaseTest {

    @Mock
    private IPaymentRepository paymentRepository;

    @InjectMocks
    CreatePaymentsUseCase createPaymentsUseCase;

    private final List<RequestPaymentMethodDTO> requestPaymentMethodDTOList = new ArrayList<>(Arrays.asList(
            new RequestPaymentMethodDTO(
                    null,
                    1L,
                    1L,
                    0.0
            )
    ));

    @Test
    void test_Create_Payments(){
        final List<PaymentEntity> paymentEntities  = new ArrayList<>();

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPaymentMethodId(requestPaymentMethodDTOList.get(0).getId());
        paymentEntity.setOriginId(requestPaymentMethodDTOList.get(0).getOriginId());
        paymentEntity.setValue(requestPaymentMethodDTOList.get(0).getValue());
        paymentEntity.setTransactionId(1L);
        paymentEntities.add(paymentEntity);

        Mockito.when(paymentRepository.saveAll(paymentEntities))
                .thenReturn(paymentEntities);

        Boolean result = createPaymentsUseCase.invoke(requestPaymentMethodDTOList, 1L)
                .join();

        assertTrue(result);
    }
}
