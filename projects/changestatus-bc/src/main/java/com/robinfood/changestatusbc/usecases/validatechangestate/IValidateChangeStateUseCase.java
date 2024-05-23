package com.robinfood.changestatusbc.usecases.validatechangestate;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;

/**
 *
 */
public interface IValidateChangeStateUseCase {

    /**
     * Validate change order state
     *
     * @param changeOrderStatusDTO Order tha wants to be change
     * @return boolean
     */
    boolean invoke (ChangeOrderStatusDTO changeOrderStatusDTO);
}
