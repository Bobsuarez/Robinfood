package com.robinfood.app.usecases.validatechangestate;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;

public interface IValidateChangeStateUseCase {

    /**
     * validate change order state
     *
     * @param changeOrderStatusDTO order tha wants to be change
     */
    boolean invoke (ChangeOrderStatusDTO changeOrderStatusDTO);
}
