package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefundStepTest {
    @Mock
    private TransactionsCommonOperations transactionsCommonOperations;

    @Mock
    private BCIProvider paymentGatewayProvider;

    @Mock
    private TransactionsConfig transactionsConfig;

    @InjectMocks
    private RefundStep refundStep;

    @Test
    void testInvokeForRefundPipeDTOShouldBeOk()
        throws PaymentMethodsException, EntityNotFoundException {
        when(
            paymentGatewayProvider.doRefund(
                anyMap(),
                anyMap(),
                anyString(),
                anyString(),
                anyString()
            )
        )
            .thenReturn(PaymentGatewaySamples.getPaymentResponseDTO());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(
            () ->
                refundStep.invoke(
                    TransactionSamples.getRefundPipeDTO()
                )
        );
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeOkWithErrorCodeNull()
        throws PaymentMethodsException, EntityNotFoundException {
        when(
            paymentGatewayProvider.doRefund(
                anyMap(), anyMap(), anyString(), anyString(), anyString()
            )
        ).thenReturn(PaymentGatewaySamples.getPaymentResponseDTOWithErrorCodeNull());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(
            () -> refundStep.invoke(
                TransactionSamples.getRefundPipeDTO()
            )
        );
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeDoNothingWhenBciProcessIsFalse() {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();
        pipe.setBciProcessRefund(false);

        assertAll(() -> refundStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeOkWhenPaymentMethodsException()
        throws PaymentMethodsException {
        when(
            paymentGatewayProvider.doRefund(
                anyMap(),
                anyMap(),
                anyString(),
                anyString(),
                anyString()
            )
        )
            .thenThrow(PaymentMethodsException.class);

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(
            () -> refundStep.invoke(TransactionSamples.getRefundPipeDTO())
        );
    }

    @Test
    void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> refundStep.invoke(new Object())
        );
    }
}
