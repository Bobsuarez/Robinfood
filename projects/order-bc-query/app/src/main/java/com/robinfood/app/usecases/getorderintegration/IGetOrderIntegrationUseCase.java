package com.robinfood.app.usecases.getorderintegration;

import com.robinfood.core.dtos.OrderIntegrationDTO;

/**
 * Use case that gets the order integration
 */
public interface IGetOrderIntegrationUseCase {

    /**
     *  Retrieves the order integration based on the following query params
     * @param orderId the order identifier
     * @return the order integration containing
     */
    OrderIntegrationDTO invoke(Long orderId);
}
