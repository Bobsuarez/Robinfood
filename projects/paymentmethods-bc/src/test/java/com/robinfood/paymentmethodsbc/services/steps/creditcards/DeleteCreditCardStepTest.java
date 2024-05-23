package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCreditCardStepTest {
    @Mock
    private CreditCardsRepository creditCardsRepository;

    @Mock
    private PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    @InjectMocks
    private DeleteCreditCardStep deleteCreditCardStep;

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOk() {
        doReturn(PaymentGatewaySamples.getPaymentGatewayCreditCard())
            .when(paymentGatewayCreditCardsRepository)
            .save(any(PaymentGatewayCreditCard.class));

        doReturn(CreditCardSamples.getCreditCard(false))
            .when(creditCardsRepository)
            .save(any(CreditCard.class));

        assertAll(
            () ->
                deleteCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardDeletePipeDTO(false)
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> deleteCreditCardStep.invoke(new Object())
        );
    }
}
