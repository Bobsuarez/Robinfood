package com.robinfood.app.usecases.getdeliverytypes;

import com.robinfood.core.dtos.DeliveryTypeDTO;

import java.util.List;

/**
 * Use case that returns the different delivery types
 */
public interface IGetDeliveryTypesUseCase {

    /**
     * Gets the delivery types from the db
     * @param isIntegration true if the order was made from one of the external delivery integrations
     *                      such as Rappi, UberEats, etc..., false otherwise
     * @param isInternalDelivery true if the order was made from one of the internal delivery services
     *                           such as Walkers, Mensajeros Urbanos, etc..., false otherwise
     * @param isOnPremise true if the order was made from one of the cloud restaurants, false otherwise
     * @return the delivery types from the db
     */
    List<DeliveryTypeDTO> invoke(
            boolean isIntegration,
            boolean isInternalDelivery,
            boolean isOnPremise
    );
}
