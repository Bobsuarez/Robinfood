package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.model.FailedTransaction;
import com.robinfood.paymentmethodsbc.model.FailedTransactionType;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.repositories.FailedTransactionRepository;
import com.robinfood.paymentmethodsbc.repositories.FailedTransactionTypeRepository;
import com.robinfood.paymentmethodsbc.services.FailedTransactionsOperations;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FailedTransactionsOperationsImpl
    implements FailedTransactionsOperations {
    private final TransactionsConfig transactionsConfig;
    private final FailedTransactionRepository failedTransactionRepository;
    private final FailedTransactionTypeRepository failedTransactionTypeRepository;

    public FailedTransactionsOperationsImpl(
        TransactionsConfig transactionsConfig,
        FailedTransactionRepository failedTransactionRepository,
        FailedTransactionTypeRepository failedTransactionTypeRepository
    ) {
        this.transactionsConfig = transactionsConfig;
        this.failedTransactionRepository = failedTransactionRepository;
        this.failedTransactionTypeRepository = failedTransactionTypeRepository;
    }

    @Override
    public FailedTransaction saveFailedTransaction(
        Transaction transaction,
        String errorCode,
        String errorDescription,
        String failedTransactionTypeName
    )
        throws EntityNotFoundException {
        FailedTransactionType type = getFailedTransactionTypeById(
            getFailedTransactionTypeIdByName(failedTransactionTypeName)
        );

        FailedTransaction failedTransaction = FailedTransaction
            .builder()
            .transaction(transaction)
            .failedTransactionType(type)
            .paymentGateway(transaction.getPaymentGateway())
            .paymentMethod(transaction.getPaymentMethod())
            .errorCode(errorCode)
            .errorDescription(errorDescription)
            .createdAt(LocalDateTime.now(ZoneOffset.UTC))
            .updatedAt(LocalDateTime.now(ZoneOffset.UTC))
            .build();

        return failedTransactionRepository.save(failedTransaction);
    }

    @Override
    public FailedTransactionType getFailedTransactionTypeById(Long id)
        throws EntityNotFoundException {
        Optional<FailedTransactionType> optional = failedTransactionTypeRepository.findById(
            id
        );

        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                FailedTransactionType.class.getSimpleName(),
                String.valueOf(id)
            );
        }

        return optional.get();
    }

    @Override
    public Long getFailedTransactionTypeIdByName(String failedTypeName) {
        return transactionsConfig.getFailedTypeId(failedTypeName);
    }
}
