package com.robinfood.app.usecases.savepickuptime;

import com.robinfood.core.models.domain.PickupTime;
import java.util.List;

public interface ISavePickupTimeUseCase {

    /**
     * Method that saves the pickup-times
     *
     * @param pickupTime to save
     * @return pickupTime saved
     */
    List<PickupTime> invoke(PickupTime pickupTime);

}
