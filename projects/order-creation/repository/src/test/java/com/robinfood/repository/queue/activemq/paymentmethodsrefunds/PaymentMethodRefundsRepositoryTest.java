package com.robinfood.repository.queue.activemq.paymentmethodsrefunds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodRefundResponseDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundResponseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

@ExtendWith(MockitoExtension.class)
class PaymentMethodRefundsRepositoryTest {

    @Mock
    private IPaymentMethodRefundsDatasource paymentMethodRefundsDatasource;

    @InjectMocks
    private PaymentMethodRefundsRepository paymentMethodRefundsRepository;

    final PaymentMethodRefundEntity paymentMethodRefundEntity = PaymentMethodRefundEntity.builder()
            .entitySourceReference("entitySourceReference")
            .entityId(1L)
            .entitySourceId(1L)
            .reason("reason")
            .build();

    final ApiResponseEntity<PaymentMethodRefundResponseEntity> paymentMethodRefundResponseEntity =
            ApiResponseEntity.<PaymentMethodRefundResponseEntity>builder()
                    .code(200)
                    .message("SOLICITUD EXITOSA")
                    .build();


    @Test
    void test_Validate_Menu_Responds_Correctly() {
        String token = "token";
        when(paymentMethodRefundsDatasource.sendRefundMessage(anyString(), any()))
                .thenReturn(CompletableFuture.completedFuture(paymentMethodRefundResponseEntity));

        PaymentMethodRefundResponseDTO paymentMethodRefundResponse = paymentMethodRefundsRepository.sendRefundMessage(
                token, paymentMethodRefundEntity).join();

        System.out.println("paymentMethodRefundResponse " + paymentMethodRefundResponse);

        assertEquals(paymentMethodRefundResponseEntity.getCode(), paymentMethodRefundResponse.getCode());
    }

}