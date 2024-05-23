package com.robinfood.usecases.replicateevent;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.EventDTO;
import com.robinfood.dtos.SubscriberDTO;

import java.util.List;

/**
 * Replicate event to the different brokers use case
 */
public interface IReplicateEventUseCase {

    void invoke(
            ChangeStatusDTO changeStatusDTO,
            EventDTO eventDTO,
            List<SubscriberDTO> subscriberDTOS,
            String token,
            String uuid
    );
}
