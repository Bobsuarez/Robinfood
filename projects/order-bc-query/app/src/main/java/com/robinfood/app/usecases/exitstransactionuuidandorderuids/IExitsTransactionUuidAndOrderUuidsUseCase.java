package com.robinfood.app.usecases.exitstransactionuuidandorderuids;

import com.robinfood.core.dtos.response.order.ResponseExistsTransactionUuidOrderUidDTO;
import java.util.List;

/**
 * Use case validates if a Transaction Uuid or Order Uids exits
 */
public interface IExitsTransactionUuidAndOrderUuidsUseCase {

    /**
     * Validates if a Transaction Uuid or Order Uids exits
     *
     * @param transactionUuid Transaction uuid
     * @param orderUids       List of Order Uids
     *
     * @return ResponseExistsTransactionUuidOrderUidDTO with a message and result of existence validation
     */
    ResponseExistsTransactionUuidOrderUidDTO invoke(String transactionUuid, List<String> orderUids);
}
