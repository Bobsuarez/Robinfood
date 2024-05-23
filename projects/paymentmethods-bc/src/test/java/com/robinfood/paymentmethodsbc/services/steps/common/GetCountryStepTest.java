package com.robinfood.paymentmethodsbc.services.steps.common;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.CountriesRepository;
import com.robinfood.paymentmethodsbc.sample.CountrySample;
import com.robinfood.paymentmethodsbc.sample.CreditCardSamples;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetCountryStepTest {
    @Mock
    private CountriesRepository countriesRepository;

    @InjectMocks
    private GetCountryStep getCountryStep;

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOk() {
        when(countriesRepository.findById(anyLong()))
            .thenReturn(Optional.of(CountrySample.getCountry()));

        assertAll(
            () ->
                getCountryStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeThrowException() {
        assertThrows(
            EntityNotFoundException.class,
            () ->
                getCountryStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeOk() {
        when(countriesRepository.findById(anyLong()))
            .thenReturn(Optional.of(CountrySample.getCountry()));

        assertAll(
            () ->
                getCountryStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForCreditCardCreatePipeDTOShouldBeThrowException() {
        assertThrows(
            EntityNotFoundException.class,
            () ->
                getCountryStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getCountryStep.invoke(new Object())
        );
    }

    @Test
    public void testInvokeForCreditCardUserListPipeDTOShouldBeOk() {
        when(countriesRepository.findById(anyLong()))
            .thenReturn(Optional.of(CountrySample.getCountry()));

        assertAll(
            () ->
                getCountryStep.invoke(
                    CreditCardSamples.getCreditCardUserListPipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForCreditCardUserListPipeDTOShouldBeThrowException() {
        assertThrows(
            EntityNotFoundException.class,
            () ->
                getCountryStep.invoke(
                    CreditCardSamples.getCreditCardCreatePipeDTO()
                )
        );
    }
}
