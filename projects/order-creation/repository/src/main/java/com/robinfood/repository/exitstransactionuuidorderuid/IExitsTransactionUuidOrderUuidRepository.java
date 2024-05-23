package com.robinfood.repository.exitstransactionuuidorderuid;

import com.robinfood.core.dtos.ExistsTransactionUuidOrderUuidDTO;

import java.util.List;

/**
 * Repository that handles to the transaction and order uuids exits
 */
public interface IExitsTransactionUuidOrderUuidRepository {

    /**
     * Returns the result of the transaction uuid and orders uuid exits
     *
     * @param token           the authorization token
     * @param transactionUuid transaction uuid
     * @param orderUuids      list of orders Uuids
     * @return message and result of the exits validation
     */
    ExistsTransactionUuidOrderUuidDTO invoke(String token, String transactionUuid, List<String> orderUuids);
}
