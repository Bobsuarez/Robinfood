package com.robinfood.repository.event;

import com.robinfood.entities.EventEntity;

public interface IEventRepository {
    EventEntity getEventByIdAndFlow(String eventId, String flowCode, String orderUuid, String token);

    EventEntity saveEvent(EventEntity eventEntity, String token);
}
