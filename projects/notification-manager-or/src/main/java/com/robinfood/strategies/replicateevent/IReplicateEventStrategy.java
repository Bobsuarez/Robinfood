package com.robinfood.strategies.replicateevent;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.SubscriberDTO;

public interface IReplicateEventStrategy {

    void execute(ChangeStatusDTO changeStatusDTO, String eventId, SubscriberDTO subscriberDTO, String token);
}
