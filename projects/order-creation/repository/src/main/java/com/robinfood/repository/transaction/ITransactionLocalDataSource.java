package com.robinfood.repository.transaction;

import com.robinfood.core.entities.ConfigTransactionResponseEntity;

public interface ITransactionLocalDataSource {

    /**
     * Return an entity of config transaction response entity
     *
     * @return the config transaction response entity
     */
    ConfigTransactionResponseEntity getTransactionResponse();

    /**
     * Get the config transaction response entity
     *
     * @param configTransactionResponseEntity the transaction response entity
     */
    void setTransactionResponse(ConfigTransactionResponseEntity configTransactionResponseEntity);
}
