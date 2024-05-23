package com.robinfood.repository.exitstransactionuuidorderuid;

import com.robinfood.core.entities.ExistsTransactionUuidOrderUuidEntity;

import java.util.List;

/**
 * Remote configuration data source that connects to external APIs to the transaction and order uuids exits
 */
public interface IExitsTransactionUuidOrderUuidDataSource {

    /**
     * Returns the result of the transaction uuid and orders uid exits
     *
     * @param token           the authorization token
     * @param transactionUuid transaction uuid
     * @param orderUuids      list of orders Uuids
     * @return message and result of the exits validation
     */
    ExistsTransactionUuidOrderUuidEntity invoke(String token, String transactionUuid, List<String> orderUuids);
}
