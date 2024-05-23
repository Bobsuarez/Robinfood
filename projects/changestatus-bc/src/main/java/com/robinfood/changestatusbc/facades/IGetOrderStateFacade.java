package com.robinfood.changestatusbc.facades;


import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.OrderStateDTO;

/**
 *
 */
public interface IGetOrderStateFacade {

    /**
     * Retrieves the state of an Order with the Uuid or Integration or order id
     *
     * @param order the order change state
     * @return an Order State DTO
     */
    OrderStateDTO invoke(ChangeOrderStatusDTO order);
}
