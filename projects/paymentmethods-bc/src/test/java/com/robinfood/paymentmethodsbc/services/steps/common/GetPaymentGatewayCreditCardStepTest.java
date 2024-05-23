package com.robinfood.paymentmethodsbc.services.steps.common;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetPaymentGatewayCreditCardStepTest {
    @Mock
    private PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    @Mock
    private CipherUtility cipherUtility;

    @InjectMocks
    private GetPaymentGatewayCreditCardStep getPaymentGatewayCreditCardStep;

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOk() {
        when(
                paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                    anyLong(),
                    anyLong()
                )
            )
            .thenReturn(
                Optional.of(PaymentGatewaySamples.getPaymentGatewayCreditCard())
            );

        when(cipherUtility.decryptTextWithPrivateKey(anyString()))
            .thenReturn("DECRYPTEDTOKEN");

        assertAll(
            () ->
                getPaymentGatewayCreditCardStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeNotCreditCardPipeShouldBeError() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getTransactionRequestDTO().getPaymentMethod().setCreditCardId(null);

        assertThrows(
            PaymentStepException.class,
            () -> getPaymentGatewayCreditCardStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeThrowException() {
        assertThrows(
            PaymentStepException.class,
            () ->
                getPaymentGatewayCreditCardStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getPaymentGatewayCreditCardStep.invoke(new Object())
        );
    }

    @Test
    public void testInvokeForTransactionValidationCreatePipeDTOShouldBeThrowException() {
        PaymentPipeDTO initial = TransactionSamples.getTransactionCreatePipeDTO();
        assertThrows(PaymentStepException.class, () -> getPaymentGatewayCreditCardStep
            .invoke(initial)
        );
    }
}
