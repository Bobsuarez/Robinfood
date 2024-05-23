package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.TransactionStatusLog;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionStatusLogsRepository
    extends JpaRepository<TransactionStatusLog, Long> {
    Optional<TransactionStatusLog> findFirstByTransactionIdOrderByIdDesc(
        Long id
    );

    Optional<TransactionStatusLog> findFirstByTransactionIdAndTransactionStatusIdOrderByIdDesc(
        Long transactionId,
        Long transactionStatusId
    );

    Optional<TransactionStatusLog> findFirstByTransactionCodeAndTransactionStatusIdOrderByIdDesc(
        String transactionCode,
        Long transactionStatusId
    );

    Optional<TransactionStatusLog> findFirstByTransactionReferenceAndTransactionStatusIdOrderByIdDesc(
        String transactionReference,
        Long transactionStatusId
    );
}
