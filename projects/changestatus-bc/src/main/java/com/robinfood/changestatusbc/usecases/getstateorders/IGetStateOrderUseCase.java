package com.robinfood.changestatusbc.usecases.getstateorders;


import com.robinfood.changestatusbc.dtos.OrderStateDTO;

public interface IGetStateOrderUseCase {

    /**
     * Retrieves the state of an Order with the idOrder
     * @param idOrder the code of the state
     * @return an Order State
     */
    OrderStateDTO invoke(Long idOrder);
    /**
     * Retrieves the state of an Order with the Uuid
     * @param uuid the uuid of the order
     * @return an Order State
     */
    OrderStateDTO invokeUuid(String uuid);

    /**
     * Retrieves the state of an Order with the Uuid
     * @param integrationId the uuid of the order
     * @return an Order State
     */
    OrderStateDTO invokeDeliveryIntegrationId(String integrationId);
}
