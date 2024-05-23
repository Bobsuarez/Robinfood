package com.robinfood.repository.pickuptime;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.models.domain.pickuptime.PickupTime;

/**
 * Contract that obtains the pickup-time of the orders of a transaction
 */
public interface IPickupTimeRepository {

    /**
     * Method that obtains the pickup-time of the orders of a transaction
     *
     * @param transactionRequestDTO request
     * @return pickup-time
     */
    PickupTime getByTransaction(
        String token,
        TransactionRequestDTO transactionRequestDTO
    );

}
