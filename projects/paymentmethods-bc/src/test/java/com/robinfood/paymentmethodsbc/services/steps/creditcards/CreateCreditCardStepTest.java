package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCreditCardStepTest {
    @Mock
    private CreditCardsRepository creditCardsRepository;

    @InjectMocks
    private CreateCreditCardStep createCreditCardStep;

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOk() {
        when(creditCardsRepository.save(any(CreditCard.class)))
            .thenReturn(CreditCardSamples.getCreditCard(false));

        assertAll(
            () ->
                createCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOWhitEmailShouldBeOk() {
        when(creditCardsRepository.save(any(CreditCard.class)))
            .thenReturn(CreditCardSamples.getCreditCardWhitEmail(false));

        assertAll(
            () ->
                createCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTOWhitDefaultEmail()
                )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOWhitDefaultEmailShouldBeOk() {
        when(creditCardsRepository.save(any(CreditCard.class)))
            .thenReturn(CreditCardSamples.getCreditCardWhitEmail(false));

        assertAll(
            () ->
                createCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTOWhitOutEmail()
                )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOWhitDifferentEmailShouldBeOk() {
        when(creditCardsRepository.save(any(CreditCard.class)))
            .thenReturn(CreditCardSamples.getCreditCardWhitDifferentEmail(false));

        assertAll(
            () ->
                createCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTOWhitDifferentEmail()
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> createCreditCardStep.invoke(new Object())
        );
    }
}
