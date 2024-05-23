package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
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
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class TransactionsCommonOperationsImpl
    implements TransactionsCommonOperations {

    private final TransactionStatusLogsRepository transactionStatusLogsRepository;
    private final TransactionsRepository transactionsRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;
    private final TransactionStatusRepository transactionStatusRepository;
    private final TransactionUsersRepository transactionUsersRepository;
    private final TransactionLogsRepository transactionLogsRepository;

    public TransactionsCommonOperationsImpl(
        TransactionStatusLogsRepository transactionStatusLogsRepository,
        TransactionsRepository transactionsRepository,
        TransactionDetailsRepository transactionDetailsRepository,
        TransactionStatusRepository transactionStatusRepository,
        TransactionUsersRepository transactionUsersRepository,
        TransactionLogsRepository transactionLogsRepository
    ) {
        this.transactionStatusLogsRepository = transactionStatusLogsRepository;
        this.transactionsRepository = transactionsRepository;
        this.transactionDetailsRepository = transactionDetailsRepository;
        this.transactionStatusRepository = transactionStatusRepository;
        this.transactionUsersRepository = transactionUsersRepository;
        this.transactionLogsRepository = transactionLogsRepository;
    }

    @BasicLog
    @Override
    public Transaction getTransactionById(Long id)
        throws EntityNotFoundException {
        Optional<Transaction> optional = transactionsRepository.findById(id);

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                Transaction.class.getSimpleName(),
                String.valueOf(id)
            );
        }

        return optional.get();
    }

    @BasicLog
    @Override
    public Transaction getTransactionByUuid(String uuid)
        throws EntityNotFoundException {
        Optional<Transaction> optional = transactionsRepository.findByUuid(
            uuid
        );

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                Transaction.class.getSimpleName(),
                String.valueOf(uuid)
            );
        }

        return optional.get();
    }

    @BasicLog
    @Override
    public Transaction saveTransaction(Transaction transaction) {
        final Transaction result = transactionsRepository.save(transaction);

        log.info(
            "transaction saved -> id: {}, succeeded: {}, updated_at: {}, transaction_status_id: {}",
            result.getId(),
            result.isSucceeded(),
            result.getUpdatedAt(),
            result.getTransactionStatus().getId()
        );

        return result;
    }

    @BasicLog
    @Override
    public TransactionDetail getTransactionDetailByTransactionId(
        Long transactionId
    )
        throws EntityNotFoundException {
        Optional<TransactionDetail> optional = transactionDetailsRepository.findById(
            transactionId
        );

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                TransactionDetail.class.getSimpleName(),
                String.valueOf(transactionId)
            );
        }

        return optional.get();
    }

    @BasicLog
    @Override
    public TransactionDetail getTransactionDetailByReference(
        final String transactionReference
    )
        throws EntityNotFoundException {
        Optional<TransactionDetail> detail = transactionDetailsRepository.findFirstByTransactionReference(
            transactionReference
        );

        if (detail.isEmpty()) {
            throw new EntityNotFoundException(
                TransactionDetail.class.getSimpleName(),
                String.valueOf(transactionReference)
            );
        }

        return detail.get();
    }

    @BasicLog
    @Override
    public TransactionDetail getTransactionDetailByCode(
        final String transactionCode
    )
        throws EntityNotFoundException {
        Optional<TransactionDetail> detail = transactionDetailsRepository.findFirstByTransactionCode(
            transactionCode
        );

        if (detail.isEmpty()) {
            throw new EntityNotFoundException(
                TransactionDetail.class.getSimpleName(),
                String.valueOf(transactionCode)
            );
        }

        return detail.get();
    }

    @BasicLog
    @Override
    public TransactionDetail saveTransactionDetail(
        TransactionDetail transactionDetail
    ) {
        return transactionDetailsRepository.save(transactionDetail);
    }

    @BasicLog
    @Override
    public TransactionStatusLog saveTransactionStatusLog(
        Transaction transaction,
        String comment,
        String transactionCode,
        String transactionReference,
        String authorizationCode
    ) {
        TransactionStatusLog transactionStatusLog = TransactionMapper.getTransactionStatusLog(
            transaction
        );

        if (Objects.isNull(comment)) {
            comment = "";
        }

        transactionStatusLog.setTransactionCode(transactionCode);
        transactionStatusLog.setTransactionReference(transactionReference);
        transactionStatusLog.setAuthorizationCode(authorizationCode);

        transactionStatusLog.setComment(comment);
        transactionStatusLog.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        transactionStatusLog.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

        log.info(
            "transactionStatusLog saved -> id: {}, created_at: {}, transaction_id: {}, transaction_status_id: {}",
            transactionStatusLog.getId(),
            transactionStatusLog.getCreatedAt(),
            transactionStatusLog.getTransaction().getId(),
            transactionStatusLog.getTransactionStatus().getId()
        );

        return transactionStatusLogsRepository.save(transactionStatusLog);
    }

    @BasicLog
    @Override
    public boolean canNotifyStatus(Transaction transaction) {
        Optional<TransactionStatusLog> lastLog = transactionStatusLogsRepository.findFirstByTransactionIdOrderByIdDesc(
            transaction.getId()
        );

        if (lastLog.isEmpty()) {
            return true;
        }

        return (
            lastLog
                .get()
                .getTransactionStatus()
                .getId()
                .compareTo(transaction.getTransactionStatus().getId()) !=
            0
        );
    }

    @Override
    public TransactionLog saveTransactionLog(
        final Transaction transaction,
        final String comment,
        final String context
    ) {
        final TransactionLog transactionLog = TransactionLog
            .builder()
            .transaction(transaction)
            .comment(comment)
            .context(context)
            .build();

        transactionLog.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        transactionLog.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));

        return transactionLogsRepository.save(transactionLog);
    }

    @Override
    public TransactionStatus getTransactionStatusById(Long id)
        throws EntityNotFoundException {
        Optional<TransactionStatus> optional = transactionStatusRepository.findById(
            id
        );

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                TransactionStatus.class.getSimpleName(),
                String.valueOf(id)
            );
        }

        return optional.get();
    }


    public TransactionUser saveUserTransaction(
        Transaction transaction,
        PaymentRequestDTO.UserDTO userTransactionDto
    ) {
        TransactionUser userTransaction = TransactionMapper.getTransactionUser(
            transaction,
            userTransactionDto
        );
        userTransaction.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        userTransaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        return transactionUsersRepository.save(userTransaction);
    }

    @Override
    public TransactionStatusLog getTransactionStatusLogByTransactionIdAndStatusId(
        Long transactionId,
        Long transactionStatusId
    )
        throws EntityNotFoundException {
        Optional<TransactionStatusLog> detail;

        detail =
            transactionStatusLogsRepository.findFirstByTransactionIdAndTransactionStatusIdOrderByIdDesc(
                transactionId,
                transactionStatusId
            );

        if (detail.isEmpty()) {
            throw new EntityNotFoundException(
                TransactionStatusLog.class.getSimpleName(),
                String.valueOf(transactionId)
            );
        }

        return detail.get();
    }

    @Override
    public TransactionStatusLog getTransactionStatusLogByCodeAndStatusId(
        String transactionCode,
        Long transactionStatusId
    )
        throws EntityNotFoundException {
        Optional<TransactionStatusLog> detail;
        detail =
            transactionStatusLogsRepository.findFirstByTransactionCodeAndTransactionStatusIdOrderByIdDesc(
                transactionCode,
                transactionStatusId
            );

        if (detail.isEmpty()) {
            throw new EntityNotFoundException(
                TransactionStatusLog.class.getSimpleName(),
                transactionCode
            );
        }

        return detail.get();
    }

    @Override
    public TransactionStatusLog getTransactionStatusLogByReferenceAndStatusId(
        String transactionReference,
        Long transactionStatusId
    )
        throws EntityNotFoundException {
        Optional<TransactionStatusLog> detail;
        detail =
            transactionStatusLogsRepository.findFirstByTransactionReferenceAndTransactionStatusIdOrderByIdDesc(
                transactionReference,
                transactionStatusId
            );

        if (detail.isEmpty()) {
            throw new EntityNotFoundException(
                TransactionStatusLog.class.getSimpleName(),
                transactionReference
            );
        }

        return detail.get();
    }
}
