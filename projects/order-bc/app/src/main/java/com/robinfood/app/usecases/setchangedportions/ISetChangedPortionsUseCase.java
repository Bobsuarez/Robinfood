package com.robinfood.app.usecases.setchangedportions;

import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;

import java.util.List;

/**
 * Use case that iterates through all the portions and sets the changed portions
 * in case there is one
 */
public interface ISetChangedPortionsUseCase {

    /**
     * Sets the changed portions for the portions of a certain final product
     * @param orderFinalProductPortionEntities the portions of a final product
     * @return the list of portions with changed portions set
     */
    List<GetOrderDetailFinalProductPortionDTO> invoke(
            List<OrderFinalProductPortionEntity> orderFinalProductPortionEntities
    );
}
