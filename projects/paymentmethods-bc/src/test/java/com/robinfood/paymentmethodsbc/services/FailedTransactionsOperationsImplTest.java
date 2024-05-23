package com.robinfood.paymentmethodsbc.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.repositories.FailedTransactionRepository;
import com.robinfood.paymentmethodsbc.repositories.FailedTransactionTypeRepository;
import com.robinfood.paymentmethodsbc.sample.FailedTransactionsSample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.impl.FailedTransactionsOperationsImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FailedTransactionsOperationsImplTest {
    @Mock
    private TransactionsConfig transactionsConfig;

    @Mock
    private FailedTransactionRepository failedTransactionRepository;

    @Mock
    private FailedTransactionTypeRepository failedTransactionTypeRepository;

    @InjectMocks
    private FailedTransactionsOperationsImpl failedTransactionsOperations;

    @Test
    public void testsaveFailedTransactionShouldBeOk()
        throws BaseException, EntityNotFoundException {
        //
        when(transactionsConfig.getFailedTypeId(anyString()))
            .thenReturn(Long.valueOf(1L));

        when(failedTransactionTypeRepository.findById(anyLong()))
            .thenReturn(
                Optional.of(FailedTransactionsSample.getFailedTransactionType())
            );

        when(failedTransactionRepository.save(any()))
            .thenReturn(FailedTransactionsSample.getFailedTransaction());

        assertAll(
            () ->
                failedTransactionsOperations.saveFailedTransaction(
                    TransactionSamples.getTransaction(),
                    "errorCode",
                    "errorDesc",
                    "INTERNAL"
                )
        );
    }

    @Test
    public void testsaveFailedTransactionShouldExceptionWhenNoTypeFound()
        throws BaseException, EntityNotFoundException {
        //
        when(transactionsConfig.getFailedTypeId(anyString()))
            .thenReturn(Long.valueOf(1L));

        when(failedTransactionTypeRepository.findById(anyLong()))
            .thenReturn(Optional.ofNullable(null));

        assertThrows(
            EntityNotFoundException.class,
            () ->
                failedTransactionsOperations.saveFailedTransaction(
                    TransactionSamples.getTransaction(),
                    "errorCode",
                    "errorDesc",
                    "INTERNAL"
                )
        );
    }
}
