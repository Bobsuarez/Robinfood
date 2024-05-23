package com.robinfood.paymentmethodsbc.services.steps.transactions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.TerminalsRepository;
import com.robinfood.paymentmethodsbc.sample.TerminalSample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDataphoneInfoStepTest {

    @Mock
    private TerminalsRepository terminalsRepository;

    @InjectMocks
    private GetDataphoneInfoStep getDataphoneInfoStep;

    @Test
    public void testInvokeShouldBeOk() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        when(
            terminalsRepository.findByUuidAndStatusAndDeletedAt(
                anyString(),
                anyBoolean(),
                any()
            )
        )
            .thenReturn(Optional.of(TerminalSample.getTerminal()));

        assertAll(() -> getDataphoneInfoStep.invoke(pipe));
    }

    @Test
    public void testInvokeWhenTerminalNotFoundShouldBeError() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        when(
            terminalsRepository.findByUuidAndStatusAndDeletedAt(
                anyString(), anyBoolean(), any()
            )
        ).thenReturn(Optional.empty());

        assertThrows(
            PaymentStepException.class,
            () -> getDataphoneInfoStep.invoke(pipe),
            "EntityNotFoundRuntimeException"
        );
    }

    @Test
    public void testInvokeWhenTerminalUuidIsNullShouldBeError() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getTransactionRequestDTO().getPaymentMethod().setTerminalUuid(null);

        assertThrows(
            PaymentStepException.class,
            () -> getDataphoneInfoStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getDataphoneInfoStep.invoke(new Object())
        );
    }

}