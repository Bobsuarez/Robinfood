package com.robinfood.repository.event;

import com.robinfood.entities.EventEntity;
import com.robinfood.network.http.api.RoutingIntegrationBcAPI;

public class EventRepository implements IEventRepository {

    private final RoutingIntegrationBcAPI routingIntegrationBcAPI;

    public EventRepository() {
        this.routingIntegrationBcAPI = new RoutingIntegrationBcAPI();
    }

    public EventRepository(RoutingIntegrationBcAPI routingIntegrationBcAPI) {
        this.routingIntegrationBcAPI = routingIntegrationBcAPI;
    }

    @Override
    public EventEntity getEventByIdAndFlow(String eventId, String flowCode, String orderUuid, String token) {
        return routingIntegrationBcAPI.getEventByIdAndFlow(flowCode, eventId, orderUuid, token);
    }

    @Override
    public EventEntity saveEvent(EventEntity eventEntity, String token) {
        return routingIntegrationBcAPI.saveEvent(eventEntity, token);
    }
}
