package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.mappers.TransactionMapper;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionLog;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.model.TransactionStatusLog;
import com.robinfood.paymentmethodsbc.model.TransactionUser;
import com.robinfood.paymentmethodsbc.repositories.TransactionDetailsRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionLogsRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionStatusLogsRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionStatusRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionUsersRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionsRepository;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.impl.TransactionsCommonOperationsImpl;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionsCommonOperationsImplTest {

    static {
        try {
            Mockito.mockStatic(TransactionMapper.class);
        } catch (Exception ignored) {
        }
    }

    @Mock
    private TransactionsConfig transactionsConfig;

    @Mock
    private TransactionStatusLogsRepository transactionStatusLogsRepository;

    @Mock
    private TransactionsRepository transactionsRepository;

    @Mock
    private TransactionDetailsRepository transactionDetailsRepository;

    @Mock
    private TransactionStatusRepository transactionStatusRepository;

    @Mock
    private TransactionUsersRepository transactionUsersRepository;

    @Mock
    private TransactionLogsRepository transactionLogsRepository;

    @InjectMocks
    private TransactionsCommonOperationsImpl transactionsCommonOperationsImpl;

    @Test
    void testGetTransactionByIdShouldBeOk() {
        when(transactionsRepository.findById(anyLong()))
            .thenReturn(Optional.of(TransactionSamples.getTransaction()));

        assertAll(
            () -> transactionsCommonOperationsImpl.getTransactionById(1L)
        );
    }

    @Test
    void testGetTransactionByIdShouldBeExceptionWhenNotFound() {
        when(transactionsRepository.findById(anyLong()))
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () -> transactionsCommonOperationsImpl.getTransactionById(1L)
        );
    }

    @Test
    void testGetTransactionByUuidShouldBeOk() {
        when(transactionsRepository.findByUuid(anyString()))
            .thenReturn(Optional.of(TransactionSamples.getTransaction()));

        assertAll(
            () ->
                transactionsCommonOperationsImpl.getTransactionByUuid(
                    anyString()
                )
        );
    }

    @Test
    void testGetTransactionByUuidShouldBeExceptionWhenNotFound() {
        when(transactionsRepository.findByUuid(anyString()))
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                transactionsCommonOperationsImpl.getTransactionByUuid(
                    anyString()
                )
        );
    }

    @Test
    void testGetTransactionDetailByTransactionIdShouldBeOk() {
        when(transactionDetailsRepository.findById(anyLong()))
            .thenReturn(Optional.of(TransactionSamples.getTransactionDetail()));

        assertAll(
            () ->
                transactionsCommonOperationsImpl.getTransactionDetailByTransactionId(
                    1L
                )
        );
    }

    @Test
    void testGetTransactionDetailByTransactionIdShouldBeExceptionWhenNotFound() {
        when(transactionDetailsRepository.findById(anyLong()))
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                transactionsCommonOperationsImpl.getTransactionDetailByTransactionId(
                    1L
                )
        );
    }

    @Test
    void testGetTransactionDetailByReferenceShouldBeOk() {
        when(
            transactionDetailsRepository.findFirstByTransactionReference(
                anyString()
            )
        )
            .thenReturn(Optional.of(TransactionSamples.getTransactionDetail()));

        assertAll(
            () ->
                transactionsCommonOperationsImpl.getTransactionDetailByReference(
                    anyString()
                )
        );
    }

    @Test
    void testGetTransactionDetailByReferenceShouldBeExceptionWhenNotFound() {
        when(
            transactionDetailsRepository.findFirstByTransactionReference(
                anyString()
            )
        )
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                transactionsCommonOperationsImpl.getTransactionDetailByReference(
                    anyString()
                )
        );
    }

    @Test
    void testGetTransactionDetailByCodeShouldBeOk() {
        when(
            transactionDetailsRepository.findFirstByTransactionCode(
                anyString()
            )
        )
            .thenReturn(Optional.of(TransactionSamples.getTransactionDetail()));

        assertAll(
            () ->
                transactionsCommonOperationsImpl.getTransactionDetailByCode(
                    anyString()
                )
        );
    }

    @Test
    void testGetTransactionDetailByCodeShouldBeExceptionWhenNotFound() {
        when(
            transactionDetailsRepository.findFirstByTransactionCode(
                anyString()
            )
        )
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                transactionsCommonOperationsImpl.getTransactionDetailByCode(
                    anyString()
                )
        );
    }

    @Test
    void testSaveTransactionDetailShouldBeOk() {
        TransactionDetail transactionDetail = new TransactionDetail();
        when(transactionDetailsRepository.save(any(TransactionDetail.class)))
            .thenReturn(transactionDetail);

        assertAll(
            () ->
                transactionsCommonOperationsImpl.saveTransactionDetail(
                    transactionDetail
                )
        );
    }

    @Test
    void testSaveTransactionShouldBeOk() {
        Transaction transaction = TransactionSamples.getTransaction();
        when(transactionsRepository.save(any(Transaction.class)))
            .thenReturn(transaction);

        assertAll(
            () -> transactionsCommonOperationsImpl.saveTransaction(transaction)
        );
    }

    @Test
    void testGetTransactionStatusByIdShouldBeOk() {
        when(transactionStatusRepository.findById(anyLong()))
            .thenReturn(Optional.of(TransactionSamples.getTransactionStatus()));

        assertAll(
            () -> transactionsCommonOperationsImpl.getTransactionStatusById(1L)
        );
    }

    @Test
    void testGetTransactionStatusByIdShouldBeExceptionWhenNotFound() {
        when(transactionStatusRepository.findById(anyLong()))
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () -> transactionsCommonOperationsImpl.getTransactionStatusById(1L)
        );
    }

    @Test
    void testSaveTransactionStatusLogShouldBeOk() {
        TransactionStatusLog transactionStatusLog = TransactionSamples.getTransactionStatusLog();

        Transaction transaction = transactionStatusLog.getTransaction();

        doReturn(transactionStatusLog).when(TransactionMapper.class);
        TransactionMapper.getTransactionStatusLog(any());

        when(transactionStatusLogsRepository.save(any()))
            .thenReturn(transactionStatusLog);

        assertAll(
            () ->
                transactionsCommonOperationsImpl.saveTransactionStatusLog(
                    transaction,
                    anyString(),
                    null,
                    null,
                    null
                )
        );
    }

    @Test
    void testSaveTransactionStatusLogCommentNullShouldBeOk() {
        TransactionStatusLog transactionStatusLog = TransactionSamples.getTransactionStatusLog();

        Transaction transaction = transactionStatusLog.getTransaction();

        doReturn(transactionStatusLog).when(TransactionMapper.class);
        TransactionMapper.getTransactionStatusLog(any());

        when(transactionStatusLogsRepository.save(any()))
            .thenReturn(transactionStatusLog);

        assertAll(
            () ->
                transactionsCommonOperationsImpl.saveTransactionStatusLog(
                    transaction,
                    null,
                    "",
                    "",
                    ""
                )
        );
    }

    @Test
    void testSaveTransactionLogShouldBeOk() {
        TransactionLog transactionLog = new TransactionLog();
        Transaction transaction = TransactionSamples.getTransaction();

        when(transactionLogsRepository.save(any(TransactionLog.class)))
            .thenReturn(transactionLog);

        assertAll(
            () ->
                transactionsCommonOperationsImpl.saveTransactionLog(
                    transaction,
                    "comment",
                    "context"
                )
        );
    }


    @Test
    void testSaveUserTransactionShouldBeOk() {
        Transaction transaction = TransactionSamples.getTransaction();
        PaymentRequestDTO.UserDTO userTransactionDto = new PaymentRequestDTO.UserDTO();
        TransactionUser transactionUser = new TransactionUser();

        doReturn(transactionUser).when(TransactionMapper.class);
        TransactionMapper.getTransactionUser(any(), any());

        when(transactionUsersRepository.save(any(TransactionUser.class)))
            .thenReturn(transactionUser);

        assertAll(
            () ->
                transactionsCommonOperationsImpl.saveUserTransaction(
                    transaction,
                    userTransactionDto
                )
        );
    }

    @Test
    void testCanNotifyStatusReturnsTrueIfNoOtherLogsPresent() {
        Transaction transaction = TransactionSamples.getTransaction();

        when(
            transactionStatusLogsRepository.findFirstByTransactionIdOrderByIdDesc(
                anyLong()
            )
        )
            .thenReturn(Optional.empty());

        assertTrue(
            transactionsCommonOperationsImpl.canNotifyStatus(transaction)
        );
    }

    @Test
    void testCanNotifyStatusReturnsFalseIfOtherLogIsSame() {
        Transaction transaction = TransactionSamples.getTransaction();
        TransactionStatusLog transactionStatusLog = TransactionStatusLog
            .builder()
            .transactionStatus(
                TransactionStatus
                    .builder()
                    .id(transaction.getTransactionStatus().getId())
                    .build()
            )
            .transaction(transaction)
            .build();

        when(
            transactionStatusLogsRepository.findFirstByTransactionIdOrderByIdDesc(
                anyLong()
            )
        )
            .thenReturn(Optional.of(transactionStatusLog));

        assertFalse(
            transactionsCommonOperationsImpl.canNotifyStatus(transaction)
        );
    }

    @Test
    void testCanNotifyStatusReturnsTrueIfOtherLogIsDifferent() {
        Transaction transaction = TransactionSamples.getTransaction();
        TransactionStatusLog transactionStatusLog = TransactionStatusLog
            .builder()
            .transactionStatus(
                TransactionStatus
                    .builder()
                    .id(transaction.getTransactionStatus().getId() + 1)
                    .build()
            )
            .transaction(transaction)
            .build();

        when(
            transactionStatusLogsRepository.findFirstByTransactionIdOrderByIdDesc(
                anyLong()
            )
        )
            .thenReturn(Optional.of(transactionStatusLog));

        assertTrue(
            transactionsCommonOperationsImpl.canNotifyStatus(transaction)
        );
    }

    @Test
    void testGetTransactionStatusLogByTransactionIdAndStatusIdShouldErrorIfNotFound() {
        when(
            transactionStatusLogsRepository.findFirstByTransactionIdAndTransactionStatusIdOrderByIdDesc(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                transactionsCommonOperationsImpl.getTransactionStatusLogByTransactionIdAndStatusId(
                    1L,
                    1L
                )
        );
    }

    @Test
    void testGetTransactionStatusLogByTransactionIdAndStatusIdShouldOk() {
        when(
            transactionStatusLogsRepository.findFirstByTransactionIdAndTransactionStatusIdOrderByIdDesc(
                anyLong(),
                anyLong()
            )
        )
            .thenReturn(
                Optional.of(TransactionSamples.getTransactionStatusLog())
            );

        assertAll(
            () ->
                transactionsCommonOperationsImpl.getTransactionStatusLogByTransactionIdAndStatusId(
                    1L,
                    1L
                )
        );
    }

    @Test
    void testGetTransactionStatusLogByTransactionCodeAndStatusIdShouldErrorIfNotFound() {
        when(
            transactionStatusLogsRepository.findFirstByTransactionCodeAndTransactionStatusIdOrderByIdDesc(
                anyString(),
                anyLong()
            )
        )
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                transactionsCommonOperationsImpl.getTransactionStatusLogByCodeAndStatusId(
                    "X",
                    1L
                )
        );
    }

    @Test
    void testGetTransactionStatusLogByTransactionCodeAndStatusIdShouldOk() {
        when(
            transactionStatusLogsRepository.findFirstByTransactionCodeAndTransactionStatusIdOrderByIdDesc(
                anyString(),
                anyLong()
            )
        )
            .thenReturn(
                Optional.of(TransactionSamples.getTransactionStatusLog())
            );

        assertAll(
            () ->
                transactionsCommonOperationsImpl.getTransactionStatusLogByCodeAndStatusId(
                    "x",
                    1L
                )
        );
    }

    @Test
    void testGetTransactionStatusLogByTransactionReferenceAndStatusIdShouldErrorIfNotFound() {
        when(
            transactionStatusLogsRepository.findFirstByTransactionReferenceAndTransactionStatusIdOrderByIdDesc(
                anyString(),
                anyLong()
            )
        )
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () ->
                transactionsCommonOperationsImpl.getTransactionStatusLogByReferenceAndStatusId(
                    "X",
                    1L
                )
        );
    }

    @Test
    void testGetTransactionStatusLogByTransactionReferenceAndStatusIdShouldOk() {
        when(
            transactionStatusLogsRepository.findFirstByTransactionReferenceAndTransactionStatusIdOrderByIdDesc(
                anyString(),
                anyLong()
            )
        )
            .thenReturn(
                Optional.of(TransactionSamples.getTransactionStatusLog())
            );

        assertAll(
            () ->
                transactionsCommonOperationsImpl.getTransactionStatusLogByReferenceAndStatusId(
                    "x",
                    1L
                )
        );
    }
}
