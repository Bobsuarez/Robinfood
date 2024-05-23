package com.robinfood.repository.transaction;

import com.robinfood.core.entities.TransactionEntity;

public class TransactionLocalDatasource implements ITransactionLocalDatasource {

    // Locally stored transaction
    private TransactionEntity transactionStored;

    public TransactionLocalDatasource() {
        // Do nothing
    }

    @Override
    public TransactionEntity getCurrentTransactionStored() {
        if (transactionStored != null) {
            return transactionStored;
        }
        return new TransactionEntity();
    }

    @Override
    public void setLocalTransactionEntity(TransactionEntity transactionEntity) {
        transactionStored = transactionEntity;
    }
}
