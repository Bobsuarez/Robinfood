package com.robinfood.app.usecases.getpickuptimebytransactionid;

import com.robinfood.core.models.domain.PickupTime;
import java.util.List;

public interface IGetPickupTimesByTransactionIdUseCase {

    /**
     * Method that obtains the pickuptimes of a transaction by means of its id
     *
     * @param transactionId to consult
     * @return {@link List<PickupTime>}
     */
    List<PickupTime> invoke(Long transactionId);

}
