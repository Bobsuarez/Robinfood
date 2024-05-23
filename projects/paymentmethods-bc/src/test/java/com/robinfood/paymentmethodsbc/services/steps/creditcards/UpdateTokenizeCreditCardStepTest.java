package com.robinfood.paymentmethodsbc.services.steps.creditcards;


import com.robinfood.paymentmethodsbc.components.providers.BCICreditCardProvider;
import com.robinfood.paymentmethodsbc.dto.CreditCardTokenDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.utils.CreditCardUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateTokenizeCreditCardStepTest {


    @Mock
    private PaymentGatewaySettingsCachedRepository settingsCachedRepository;
    @Mock
    private BCICreditCardProvider creditCardProvider;

    @InjectMocks
    private UpdateTokenizeCreditCardStep updateTokenizeCreditCardStep;


    static {
        try {
            mockStatic(CreditCardUtils.class);
        } catch (Exception e) {}
    }


    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeOk() throws PaymentMethodsException {
             
        when(
            settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong())
        )
        .thenReturn(
            PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap("orchestratorId","1")
        );

        when(
            settingsCachedRepository.getSettingsOrchestratorPaymentGateway(
                anyLong(),
                anyLong()
            )
        ).thenReturn(new HashMap<>());


        when(
            creditCardProvider.updateToken(
                anyMap(),
                anyMap(),
                any(CreditCardTokenDTO.class),
                anyString()
            )
        ).thenReturn("TOKEN ID");
        

        assertAll(
            ()-> updateTokenizeCreditCardStep.invoke(
                CreditCardSamples.getCreditCardUpdatedPipeDTO(
                    CreditCardSamples.getCreditCard(false)
                )
            )
        );
    }



    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenPaymentGatewayNotConfigure() throws PaymentMethodsException {
       
        when(
            settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong())
        )
        .thenReturn(
            Collections.emptyMap()
        );


        assertThrows( 
            PaymentStepException.class
            ,() -> updateTokenizeCreditCardStep.invoke(
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
            updateTokenizeCreditCardStep.invoke(null)
        );
    
    }

}
