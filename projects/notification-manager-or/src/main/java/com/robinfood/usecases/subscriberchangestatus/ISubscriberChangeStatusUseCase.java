package com.robinfood.usecases.subscriberchangestatus;

import com.robinfood.dtos.ChangeStatusDTO;

/**
 * Subscriber change status use case
 */
public interface ISubscriberChangeStatusUseCase {
    void invoke(ChangeStatusDTO changeStatusDTO, String eventId);
}
