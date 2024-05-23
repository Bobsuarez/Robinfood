package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserListCreditCardStepTest {
    @Mock
    private PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    @Mock
    private CreditCardsRepository creditCardsRepository;

    @Mock
    private CipherUtility cipherUtility;

    @InjectMocks
    private UserListCreditCardStep userListCreditCardStep;

    @Test
    public void testInvokeForCreditCardUserListPipeDTOShouldBeOk() {
        when(
                creditCardsRepository.findByUserIdAndCountryIdAndDeletedAt(
                    anyLong(),
                    anyLong(),
                    nullable(LocalDateTime.class)
                )
            )
            .thenReturn(CreditCardSamples.getCreditCardList());

        when(
                paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayIdAndDeletedAtIsNull(
                    anyList(),
                    anyLong()
                )
            )
            .thenReturn(
                List.of(PaymentGatewaySamples.getPaymentGatewayCreditCard())
            );

        when(cipherUtility.decryptTextWithPrivateKey(anyString()))
            .thenReturn("x");

        assertAll(
            () ->
                userListCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUserListPipeDTO()
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> userListCreditCardStep.invoke(new Object())
        );
    }
}
