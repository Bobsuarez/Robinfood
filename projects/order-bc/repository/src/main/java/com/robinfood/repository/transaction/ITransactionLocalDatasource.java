package com.robinfood.repository.transaction;

import com.robinfood.core.entities.TransactionEntity;

/**
 * Interface in order to manage the local datasource related to transaction data
 */
public interface ITransactionLocalDatasource {

    /**
     * Returns the current stored transaction
     *
     * @return the current stored transaction
     */
    TransactionEntity getCurrentTransactionStored();

    /**
     * Set the transaction entity data stored locally
     *
     * @param transactionEntity the transaction data
     */
    void setLocalTransactionEntity(TransactionEntity transactionEntity);

}
