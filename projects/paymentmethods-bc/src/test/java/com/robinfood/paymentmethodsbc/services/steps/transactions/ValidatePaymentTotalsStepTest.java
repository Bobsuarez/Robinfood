package com.robinfood.paymentmethodsbc.services.steps.transactions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidatePaymentTotalsStepTest {
    @InjectMocks
    private ValidatePaymentTotalsStep validatePaymentTotalsStep;

    @Test
    public void testInvokeValidatePaymentTotalStepShouldBeOk()
        throws BaseException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        PaymentDTO payment = PaymentRequestDTO
            .PaymentDTO.builder()
            .subtotal(BigDecimal.TEN)
            .tax(BigDecimal.ONE)
            .total(BigDecimal.valueOf(11))
            .build();

        pipe.getTransactionRequestDTO().setPayment(payment);

        assertAll(() -> validatePaymentTotalsStep.invoke(pipe));
    }

    @Test
    public void testInvokeValidatePaymentTotalStepShouldBeErrorDiffTotal()
        throws PaymentStepException {
        assertThrows(
            PaymentStepException.class,
            () ->
                validatePaymentTotalsStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeValidatePaymentTotalStepShouldBeErrorNotInstanceof() {
        assertThrows(
            PaymentStepException.class,
            () ->
                validatePaymentTotalsStep.invoke(
                    TransactionSamples.getTransactionDetail()
                )
        );
    }
}
