package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;

@ExtendWith(MockitoExtension.class)
public class UpdateCreditCardStepTest {
    
    @Mock
    private  CreditCardsRepository creditCardsRepository;

    @Mock
    private  PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    @InjectMocks
    private UpdateCreditCardStep updateCreditCardStep;

    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeOk() {
        when(creditCardsRepository.save(
            any(CreditCard.class))
        )
        .thenReturn(
            CreditCardSamples.getCreditCard(false)
        );

        when(
            paymentGatewayCreditCardsRepository.save(
                any(PaymentGatewayCreditCard.class)
            )
        )
        .thenReturn(
            PaymentGatewaySamples.getPaymentGatewayCreditCard()
        );
        
        assertAll(
            () ->
                updateCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUpdatedPipeDTO(
                        CreditCardSamples.getCreditCard(false)
                    )
                )
        );


    }

    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenNullInstance(){
    
        assertThrows(
            PaymentStepException.class,
            () ->
                updateCreditCardStep.invoke(null)
        );
    
    }

}
