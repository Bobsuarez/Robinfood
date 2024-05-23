package com.robinfood.changestatusbc.usecases.changestatewithsubstate;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;

/**
 *
 */
public interface IChangeStateWithSubStateUseCase {

    /**
     * Updates orders status
     *
     * @param idOrder
     * @param nextState
     * @return Boolean
     */
    Boolean invoke (Long idOrder, OrderStateDTO nextState);
}
