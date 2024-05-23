package com.robinfood.app.usecases.getdeliverytypes;

import com.robinfood.core.dtos.DeliveryTypeDTO;

import java.util.List;

/**
 * Use case that returns the different delivery types
 */
public interface IGetDeliveryTypesUseCase {

    /**
     * Gets the delivery types from the db
     * @return the delivery types from the db
     */
    List<DeliveryTypeDTO> invoke();
}
