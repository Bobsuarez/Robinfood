package com.robinfood.paymentmethodsbc.services.steps.transactions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.repositories.TransactionDetailsRepository;
import com.robinfood.paymentmethodsbc.sample.TransactionDetailSample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.sample.TransactionStatusSample;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CancelPreviousTransactionStepTest {

    @Mock
    private TransactionDetailsRepository transactionDetailsRepository;
    @Mock
    private TransactionsCommonOperations transactionsCommonOperations;
    @Mock
    private TransactionsConfig transactionsConfig;
    @Mock
    private JmsSendStatusTransactionStep jmsSendStatusTransactionStep;
    @Mock
    private BCIProvider bciProvider;
    @InjectMocks
    private CancelPreviousTransactionStep cancelPreviousTransactionStep;

    @Test
    public void testInvokeShouldBeOk() throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getSettings().getGatewayConfig().put("cancelPreviousTransaction", "true");
        pipe.getTransaction().setId(2L);

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        when(
            transactionDetailsRepository.findByTransactionTransactionStatusIdAndTerminalIdAndTransactionCodeIsNotNull(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(List.of(TransactionDetailSample.getTransactionDetail()));

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeSettingsNullShouldBeOk() throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.setSettings(null);

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeGatewayConfigNullShouldBeOk() throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getSettings().setGatewayConfig(null);

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokePropertyNotFoundShouldBeOk() throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokePropertyDisabledShouldBeOk() throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getSettings().getGatewayConfig().put("cancelPreviousTransaction", "false");

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeGetTransactionStatusFailedShouldBeOk() throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getSettings().getGatewayConfig().put("cancelPreviousTransaction", "true");
        pipe.getTransaction().setId(2L);

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenThrow(EntityNotFoundException.class);

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeWithTransactionEqualShouldBeOk() throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getSettings().getGatewayConfig().put("cancelPreviousTransaction", "true");

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        when(
            transactionDetailsRepository.findByTransactionTransactionStatusIdAndTerminalIdAndTransactionCodeIsNotNull(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(List.of(TransactionDetailSample.getTransactionDetail()));

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeCancelDisabledShouldBeOk() {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeCancelTransactionGenerateErrorShouldBeOk()
        throws PaymentMethodsException, EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getSettings().getGatewayConfig().put("cancelPreviousTransaction", "true");
        pipe.getTransaction().setId(2L);

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        when(
            transactionDetailsRepository.findByTransactionTransactionStatusIdAndTerminalIdAndTransactionCodeIsNotNull(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(List.of(TransactionDetailSample.getTransactionDetail()));

        when(
            bciProvider.doCancelTransaction(
                any(SettingsDTO.class),
                anyString()
            )
        )
            .thenThrow(PaymentMethodsException.class);

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }

    @Test
    public void testInvokeNotifyCancellationGenerateErrorShouldBeOk()
        throws PaymentStepException, EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getSettings().getGatewayConfig().put("cancelPreviousTransaction", "true");
        pipe.getTransaction().setId(2L);

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(1L);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionStatusSample.getTransactionStatus());

        when(
            transactionDetailsRepository.findByTransactionTransactionStatusIdAndTerminalIdAndTransactionCodeIsNotNull(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(List.of(TransactionDetailSample.getTransactionDetail()));

        doThrow(PaymentStepException.class)
            .when(jmsSendStatusTransactionStep)
            .invoke(any(PaymentPipeDTO.class));

        assertAll(
            () -> cancelPreviousTransactionStep.invoke(pipe)
        );
    }


    @Test
    public void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> cancelPreviousTransactionStep.invoke(new Object())
        );
    }
}