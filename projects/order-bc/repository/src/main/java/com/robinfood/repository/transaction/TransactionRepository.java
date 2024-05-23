package com.robinfood.repository.transaction;

import com.robinfood.core.entities.TransactionEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository implements ITransactionRepository {

    private final ITransactionLocalDatasource transactionLocalDatasource;

    public TransactionRepository(ITransactionLocalDatasource transactionLocalDatasource) {
        this.transactionLocalDatasource = transactionLocalDatasource;
    }

    @Override
    public TransactionEntity getLocalTransaction() {
        return transactionLocalDatasource.getCurrentTransactionStored();
    }

    @Override
    public void setLocalTransaction(TransactionEntity transactionEntity) {
        transactionLocalDatasource.setLocalTransactionEntity(transactionEntity);
    }
}
