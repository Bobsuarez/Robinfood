package com.robinfood.app.usecases.getpickuptime;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.models.domain.pickuptime.PickupTime;

/**
 * Contract that obtains the pickup-time of the orders of a transaction
 */
public interface IGetPickupTimeUseCase {

    /**
     * Method that obtains the pickup-time of the orders of a transaction
     *
     * @param transactionRequestDTO
     * @return PickupTime
     */
    PickupTime invoke(
            String token,
            TransactionRequestDTO transactionRequestDTO
    );

}
