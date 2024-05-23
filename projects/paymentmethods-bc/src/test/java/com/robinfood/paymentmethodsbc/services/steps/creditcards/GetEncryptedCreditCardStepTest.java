package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.steps.CreditCardCreatePipeDTO;
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
public class GetEncryptedCreditCardStepTest {
    @Mock
    private CreditCardsRepository creditCardsRepository;

    @Mock
    private CipherUtility cipherUtility;

    @InjectMocks
    private GetEncryptedCreditCardStep getEncryptedCreditCardStep;

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeGetGenerateTokenSetOneOk() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();

        when(creditCardsRepository.findById(anyLong()))
            .thenReturn(Optional.of(CreditCardSamples.getCreditCard(false)));

        when(cipherUtility.translateEncryptionPrivate(anyString()))
            .thenReturn("x");

        assertAll(() -> getEncryptedCreditCardStep.invoke(pipe));
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeGetGenerateTokenSetCeroOk() {
        CreditCardCreatePipeDTO pipe = CreditCardSamples.getCreditCardCreatePipeDTO();
        pipe.setGeneratedTokens(0);

        when(creditCardsRepository.findById(anyLong()))
            .thenReturn(Optional.of(CreditCardSamples.getCreditCard(false)));

        assertAll(() -> getEncryptedCreditCardStep.invoke(pipe));
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowException() {
        assertThrows(
            EntityNotFoundException.class,
            () ->
                getEncryptedCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getEncryptedCreditCardStep.invoke(new Object())
        );
    }
}
