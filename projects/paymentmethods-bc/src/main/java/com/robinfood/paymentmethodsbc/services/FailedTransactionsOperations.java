package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.model.FailedTransaction;
import com.robinfood.paymentmethodsbc.model.FailedTransactionType;
import com.robinfood.paymentmethodsbc.model.Transaction;

public interface FailedTransactionsOperations {
    FailedTransaction saveFailedTransaction(
        Transaction transaction,
        String errorCode,
        String errorDescription,
        String failedTransactionTypeName
    )
        throws EntityNotFoundException;

    FailedTransactionType getFailedTransactionTypeById(Long id)
        throws EntityNotFoundException;

    Long getFailedTransactionTypeIdByName(String failedTypeName);
}
