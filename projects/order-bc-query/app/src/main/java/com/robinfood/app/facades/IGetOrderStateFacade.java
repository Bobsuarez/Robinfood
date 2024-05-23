package com.robinfood.app.facades;

import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;

public interface IGetOrderStateFacade {
    /**
     * Retrieves the state of an Order with the Uuid or Integration or order id
     * @param order the order change state
     * @return an Order State DTO
     */
    OrderStateDTO invoke(ChangeOrderStatusDTO order);
}
