package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RemoveTokenCreditCardStepTest {
    @Mock
    private CipherUtility cipherUtility;

    @Mock
    private PaymentGatewaySettingsCachedRepository settingsCachedRepository;

    @Mock
    private PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    @Mock
    private BCIProvider paymentGatewayProvider;

    @InjectMocks
    private RemoveTokenCreditCardStep removeTokenCreditCardStep;

    @Test
    public void testInvokeForCreditCardDeletePipeDTOShouldBeOk() throws PaymentMethodsException {

        when(settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong()))
            .thenReturn(PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap());

        when(
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                anyLong(), anyLong()
            )
        ).thenReturn(Optional.of(PaymentGatewaySamples.getPaymentGatewayCreditCard()));

        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("token");

        doNothing().when(paymentGatewayProvider)
            .doCreditCardRemove(anyMap(), anyMap(), anyString(), anyString());

        assertAll(
            () -> removeTokenCreditCardStep.invoke(
                CreditCardSamples.getCreditCardDeletePipeDTO(true)
            )
        );
    }

    @Test
    public void testInvokeForCreditCardDeletePipeDTOShouldBeOkWhenNoCreditCardAndGateway()
        throws PaymentMethodsException {

        when(settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong()))
            .thenReturn(PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap());

        when(
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                anyLong(), anyLong()
            )
        ).thenReturn(Optional.ofNullable(null));

        assertAll(
            () -> removeTokenCreditCardStep.invoke(
                CreditCardSamples.getCreditCardDeletePipeDTO(true)
            )
        );
    }

    @Test
    public void testInvokeForCreditCardDeletePipeDTOShouldBeOkWhenTokenCannotBeDecrypted()
        throws PaymentMethodsException {

        when(settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong()))
            .thenReturn(PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap());

        when(
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                anyLong(), anyLong()
            )
        ).thenReturn(Optional.of(PaymentGatewaySamples.getPaymentGatewayCreditCard()));

        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn(null);

        assertAll(
            () -> removeTokenCreditCardStep.invoke(
                CreditCardSamples.getCreditCardDeletePipeDTO(true)
            )
        );
    }

    @Test
    public void testInvokeForCreditCardDeletePipeDTOShouldBeOkWhenPaymentMethodException()
        throws PaymentMethodsException {

        when(settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong()))
            .thenReturn(PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap());

        when(
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                anyLong(), anyLong()
            )
        ).thenReturn(Optional.of(PaymentGatewaySamples.getPaymentGatewayCreditCard()));

        when(cipherUtility.decryptTextWithPrivateKey(anyString())).thenReturn("token");

        doThrow(PaymentMethodsException.class).when(paymentGatewayProvider)
            .doCreditCardRemove(anyMap(), anyMap(), anyString(), anyString());

        assertAll(
            () -> removeTokenCreditCardStep.invoke(
                CreditCardSamples.getCreditCardDeletePipeDTO(true)
            )
        );
    }

    @Test
    public void testInvokeForCreditCardDeletePipeDTOThrowsException() {

        when(settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong()))
            .thenReturn(PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap());

        when(
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                anyLong(), anyLong()
            )
        ).thenReturn(Optional.of(PaymentGatewaySamples.getPaymentGatewayCreditCard()));

        doThrow(NullPointerException.class).when(cipherUtility).decryptTextWithPrivateKey(anyString());

        assertAll(
            () -> removeTokenCreditCardStep.invoke(
                CreditCardSamples.getCreditCardDeletePipeDTO(true)
            )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> removeTokenCreditCardStep.invoke(new Object()),
            "BaseException"
        );
    }
}
