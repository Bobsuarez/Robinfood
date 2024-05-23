package com.robinfood.app.usecases.getorderisintegration;

/**
 * Use case get order is integration
 */
public interface IGetOrderIsIntegrationUseCase {

    /**
     * Check if the order is integration
     *
     * @param orderId
     * @return True if the order is integration
     */
    Boolean invoke(Long orderId);
}
