package com.robinfood.repository.transaction;

import com.robinfood.core.entities.ConfigTransactionResponseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of ITransactionLocalDataSource
 */
@Slf4j
public class TransactionLocalDataSource implements ITransactionLocalDataSource {

    private ConfigTransactionResponseEntity privateConfigTransactionResponseEntity;

    public TransactionLocalDataSource() {
    }

    public TransactionLocalDataSource(ConfigTransactionResponseEntity privateConfigTransactionResponseEntity) {
        this.privateConfigTransactionResponseEntity = privateConfigTransactionResponseEntity;
    }

    @Override
    public ConfigTransactionResponseEntity getTransactionResponse() {
        return privateConfigTransactionResponseEntity;
    }

    @Override
    public void setTransactionResponse(ConfigTransactionResponseEntity configTransactionResponseEntity) {
        privateConfigTransactionResponseEntity = configTransactionResponseEntity;
    }
}
