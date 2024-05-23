package com.robinfood.paymentmethodsbc.services.steps.creditcards;

import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCardType;
import com.robinfood.paymentmethodsbc.repositories.CreditCardTypesRepository;
import com.robinfood.paymentmethodsbc.repositories.CreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewayCreditCardsRepository;
import com.robinfood.paymentmethodsbc.repositories.PaymentGatewaySettingsCachedRepository;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.utils.CreditCardUtils;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BuildUpdateCreditCardStepTest {

    @Mock
    private  CreditCardsRepository creditCardsRepository;

    @Mock
    private  PaymentGatewaySettingsCachedRepository settingsCachedRepository;

    @Mock
    private  PaymentGatewayCreditCardsRepository paymentGatewayCreditCardsRepository;

    @Mock
    private  CreditCardTypesRepository creditCardTypesRepository;

    @InjectMocks
    private BuildUpdateCreditCardStep buildUpdateCreditCardStep;

    static {
        try {
            mockStatic(CreditCardUtils.class);
        } catch (Exception e) {}
    }


    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeOk() {
    
        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), 
                anyLong(),
                nullable(LocalDateTime.class)
            )
        ).thenReturn(
            Optional.of(
                CreditCardSamples.getCreditCard(true)
            )
        );

    
        when(CreditCardUtils.decryptTextWithPrivateKey(anyString())).thenReturn(
            CreditCardSamples.getCreditCardRequestDTO().getNumber()
        );

        when(creditCardTypesRepository.findByCode(anyString())).thenReturn(
            Optional.of(
                CreditCardType.builder().id(1L).build()
            )
        );


        when(
            settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong())
        )
        .thenReturn(
            PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap()
        );

        when(
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                anyLong(), anyLong()
            )
        ).thenReturn(
            Optional.of(PaymentGatewaySamples.getPaymentGatewayCreditCard())
        );


        assertAll(()-> buildUpdateCreditCardStep.invoke(
            CreditCardSamples.getCreditCardUpdatePipeDTO(null))
        );


    }


    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenCreditCardNotFound(){

        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), 
                anyLong(),
                nullable(LocalDateTime.class)
            )
        ).thenReturn(
            Optional.empty()
        );

        assertThrows(
            EntityNotFoundException.class,
            () ->
                buildUpdateCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUpdatePipeDTO(null)
                )
        );
    }


    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenPaymentGatewayCreditCardNotFound(){
    

        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), 
                anyLong(),
                nullable(LocalDateTime.class)
            )
        ).thenReturn(
            Optional.of(
                CreditCardSamples.getCreditCard(false)
            )
        );
    
        when(CreditCardUtils.decryptTextWithPrivateKey(anyString())).thenReturn(
            CreditCardSamples.getCreditCardRequestDTO().getNumber()
        );

        when(creditCardTypesRepository.findByCode(anyString())).thenReturn(
            Optional.of(
                CreditCardType.builder().id(1L).build()
            )
        );


        when(
            settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong())
        )
        .thenReturn(
            PaymentGatewaySamples.getAllCountryPaymentGatewaySettingMap()
        );

        when(
            paymentGatewayCreditCardsRepository.findByCreditCardIdAndPaymentGatewayId(
                anyLong(), anyLong()
            )
        ).thenReturn(
            Optional.empty()
        );


        assertThrows(
            PaymentStepException.class,
            () ->
                buildUpdateCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUpdatePipeDTO(null)
                )
        );
    
    }



    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenPaymentGatewaySettingsByCountryForTokenizationNotFound(){
    

        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), 
                anyLong(),
                nullable(LocalDateTime.class)
            )
        ).thenReturn(
            Optional.of(
                CreditCardSamples.getCreditCard(false)
            )
        );
    
    
        when(CreditCardUtils.decryptTextWithPrivateKey(anyString())).thenReturn(
            CreditCardSamples.getCreditCardRequestDTO().getNumber()
        );

        when(creditCardTypesRepository.findByCode(anyString())).thenReturn(
            Optional.of(
                CreditCardType.builder().id(1L).build()
            )
        );


        when(
            settingsCachedRepository.getPaymentGatewaySettingsByCountryForTokenization(anyLong())
        )
        .thenReturn(
            Collections.emptyMap()
        );


        assertThrows(
            PaymentStepException.class,
            () ->
                buildUpdateCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUpdatePipeDTO(null)
                )
        );
    
    }



    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenCompanyCreditCardIsNull() {
    
        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), 
                anyLong(),
                nullable(LocalDateTime.class)
            )
        ).thenReturn(
            Optional.of(
                CreditCardSamples.getCreditCard(true)
            )
        );

        when(CreditCardUtils.decryptTextWithPrivateKey(anyString())).thenReturn(
            null
        );

        assertThrows(
            PaymentStepException.class,
            () ->
                buildUpdateCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUpdatePipeDTO(null)
                )
        );

    }

    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenCreditCardTypeNotFound(){
    
        when(
            creditCardsRepository.findByIdAndUserIdAndDeletedAt(
                anyLong(), 
                anyLong(),
                nullable(LocalDateTime.class)
            )
        ).thenReturn(
            Optional.of(
                CreditCardSamples.getCreditCard(false)
            )
        );
    
    
        when(CreditCardUtils.decryptTextWithPrivateKey(anyString())).thenReturn(
            CreditCardSamples.getCreditCardRequestDTO().getNumber()
        );

        when(creditCardTypesRepository.findByCode(anyString())).thenReturn(
            Optional.empty()
        );

        assertThrows(
            EntityNotFoundException.class,
            () ->
                buildUpdateCreditCardStep.invoke(
                    CreditCardSamples.getCreditCardUpdatePipeDTO(null)
                )
        );
    
    }


    @Test
    public void testInvokeForCreditCardUpdatePipeShouldBeErrorWhenNullInstance(){
    
        assertThrows(
            PaymentStepException.class,
            () ->
                buildUpdateCreditCardStep.invoke(null)
        );
    
    }
}
