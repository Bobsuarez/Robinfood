package com.robinfood.changestatusor.usecases.validatechangestate;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;

public interface IValidateChangeStateUseCase {

    /**
     * validate change order state
     *
     * @param changeOrderStatusDTO order tha wants to be change
     */
    boolean invoke (ChangeOrderStatusDTO changeOrderStatusDTO);
}
