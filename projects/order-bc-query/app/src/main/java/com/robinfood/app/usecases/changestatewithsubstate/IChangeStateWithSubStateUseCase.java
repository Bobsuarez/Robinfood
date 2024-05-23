package com.robinfood.app.usecases.changestatewithsubstate;

import com.robinfood.core.dtos.OrderStateDTO;

public interface IChangeStateWithSubStateUseCase {

    /**
     * Updates orders status
     * @param idOrder the id of an Order
     * @param nextState the next State to change
     */
    Boolean invoke (Long idOrder, OrderStateDTO nextState);

}
