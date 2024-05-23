package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.TransactionMapper;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InitialTransactionStepTest {

    static {
        try {
            Mockito.mockStatic(TransactionMapper.class);
        } catch (Exception ignore) {
        }
    }

    @Mock
    private TransactionsCommonOperations transactionsCommonOperations;

    @Mock
    private TransactionsConfig transactionsConfig;

    @InjectMocks
    private InitialTransactionStep initialTransactionStep;

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOk()
        throws EntityNotFoundException {
        Transaction transaction = TransactionSamples.getTransaction();
        TransactionDetail transactionDetail = TransactionSamples.getTransactionDetail();

        doReturn(transaction).when(TransactionMapper.class);
        TransactionMapper.getTransactionFromCreateDTO(any());

        doReturn(transactionDetail).when(TransactionMapper.class);
        TransactionMapper.getTransactionDetailInitial(any(), any());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.saveTransaction(any()))
            .thenReturn(transaction);

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        when(transactionsCommonOperations.saveTransactionDetail(any()))
            .thenReturn(transactionDetail);

        assertAll(
            () ->
                initialTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeOk()
        throws EntityNotFoundException {
        Transaction transaction = TransactionSamples.getTransaction();
        TransactionDetail transactionDetail = TransactionSamples.getTransactionDetail();

        doReturn(transaction).when(TransactionMapper.class);
        TransactionMapper.mapRefundFromTransaction(any());

        doReturn(transactionDetail).when(TransactionMapper.class);
        TransactionMapper.getTransactionDetailInitial(any(), any());

        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

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

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        assertAll(() -> initialTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeOkWhenBciProcessFalse()
        throws EntityNotFoundException {
        Transaction transaction = TransactionSamples.getTransaction();
        TransactionDetail transactionDetail = TransactionSamples.getTransactionDetail();

        doReturn(transaction).when(TransactionMapper.class);
        TransactionMapper.mapRefundFromTransaction(any());

        doReturn(transactionDetail).when(TransactionMapper.class);
        TransactionMapper.getTransactionDetailInitial(any(), any());

        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();
        pipe.setBciProcessRefund(false);

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

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

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        assertAll(() -> initialTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> initialTransactionStep.invoke(new Object())
        );
    }
}
