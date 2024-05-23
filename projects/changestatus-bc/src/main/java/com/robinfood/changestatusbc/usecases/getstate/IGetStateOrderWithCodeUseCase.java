package com.robinfood.changestatusbc.usecases.getstate;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;

/**
 *
 */
public interface IGetStateOrderWithCodeUseCase {

    /**
     * Retrieves the state of an Order with the code
     * @param code the code of the state
     * @return an Order State
     */
    OrderStateDTO invoke(String code);
}
