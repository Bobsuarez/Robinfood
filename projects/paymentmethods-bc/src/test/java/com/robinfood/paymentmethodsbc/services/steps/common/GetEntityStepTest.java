package com.robinfood.paymentmethodsbc.services.steps.common;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.EntitiesRepository;
import com.robinfood.paymentmethodsbc.sample.EntitySample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetEntityStepTest {
    @Mock
    private EntitiesRepository entitiesRepository;

    @InjectMocks
    private GetEntityStep getEntityStep;

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeOk() {
        when(entitiesRepository.findById(anyLong()))
            .thenReturn(Optional.of(EntitySample.getEntity()));

        assertAll(
            () ->
                getEntityStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeForTransactionCreatePipeDTOShouldBeThrowException() {
        assertThrows(
            EntityNotFoundException.class,
            () ->
                getEntityStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getEntityStep.invoke(new Object())
        );
    }
}
