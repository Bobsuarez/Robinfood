package com.robinfood.repository.transaction;

import com.robinfood.core.entities.TransactionEntity;

/**
 * Repository that handles transaction data
 */
public interface ITransactionRepository {

    /**
     * Returns the current stored transaction from local datasource
     *
     * @return current stored transaction
     */
    TransactionEntity getLocalTransaction();

    /**
     * Set the transaction entity data stored in local datasource
     *
     * @param transactionEntity the transaction data to be stored
     */
    void setLocalTransaction(TransactionEntity transactionEntity);
}
