package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetDecryptedCreditCardStepTest {
    

    @Mock
    private CreditCardsRepository creditCardsRepository;

    @Mock
    private CipherUtility cipherUtility;

    @InjectMocks
    private GetDecryptedCreditCardStep getDecryptedCreditCardStep;


    @Test
    public void testInvokeForCreditCardUpdatePipeDTOShouldBeOk() {

        when(creditCardsRepository.findById(anyLong()))
            .thenReturn(Optional.of(CreditCardSamples.getCreditCard(false)));

        when(cipherUtility.decryptTextWithPrivateKey(anyString()))
            .thenReturn("x");

        assertAll(
            () ->
                getDecryptedCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUpdatedPipeDTO(
                        CreditCardSamples.getCreditCard(true)
                    )
                )
        );
    
    }



    @Test
    public void testInvokeForCreditCardUpdatePipeDTOShouldBeErrorWhenCreditCardNotExists() {

        when(creditCardsRepository.findById(anyLong()))
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                getDecryptedCreditCardStep.invoke(
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
                getDecryptedCreditCardStep.invoke(null)
        );
    
    }

}
