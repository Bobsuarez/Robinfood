package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenizeCreditCardStepTest {
    @Mock
    private PaymentGatewaySettingsCachedRepository settingsCachedRepository;

    @Mock
    private PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    @Mock
    private BCIProvider paymentGatewayProvider;

    @InjectMocks
    private TokenizeCreditCardStep tokenizeCreditCardStep;

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOk()
        throws PaymentMethodsException {
        when(
                settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(
                    anyLong()
                )
            )
            .thenReturn(
                PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap()
            );

        when(
                paymentGatewayProvider.doCreditCardTokenization(
                    anyMap(),
                    anyMap(),
                    any(CreditCardTokenDTO.class)
                )
            )
            .thenReturn("TOKEN");

        when(
                paymentGatewayCreditCardsRepository.save(
                    any(PaymentGatewayCreditCard.class)
                )
            )
            .thenReturn(PaymentGatewaySamples.getPaymentGatewayCreditCard());

        assertAll(
            () ->
                tokenizeCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOkWhenNoTokenGenerated()
        throws PaymentMethodsException {
        when(
                settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(
                    anyLong()
                )
            )
            .thenReturn(
                PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap()
            );

        when(
                paymentGatewayProvider.doCreditCardTokenization(
                    anyMap(),
                    anyMap(),
                    any(CreditCardTokenDTO.class)
                )
            )
            .thenReturn(null);

        assertAll(
            () ->
                tokenizeCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOkWhenPaymentMethodException()
        throws PaymentMethodsException {
        when(
                settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(
                    anyLong()
                )
            )
            .thenReturn(
                PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap()
            );

        when(
                paymentGatewayProvider.doCreditCardTokenization(
                    anyMap(),
                    anyMap(),
                    any(CreditCardTokenDTO.class)
                )
            )
            .thenThrow(PaymentMethodsException.class);

        assertAll(
            () ->
                tokenizeCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> tokenizeCreditCardStep.invoke(new Object())
        );
    }
}
