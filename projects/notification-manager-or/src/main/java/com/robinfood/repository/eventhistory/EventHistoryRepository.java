package com.robinfood.repository.eventhistory;

import com.robinfood.entities.EventHistoryEntity;
import com.robinfood.network.http.api.RoutingIntegrationBcAPI;

public class EventHistoryRepository implements IEventHistoryRepository {

    private final RoutingIntegrationBcAPI routingIntegrationBcAPI;

    public EventHistoryRepository() {
        this.routingIntegrationBcAPI = new RoutingIntegrationBcAPI();
    }

    public EventHistoryRepository(RoutingIntegrationBcAPI routingIntegrationBcAPI) {
        this.routingIntegrationBcAPI = routingIntegrationBcAPI;
    }

    @Override
    public void saveEventHistory(EventHistoryEntity eventHistoryEntity, String token) {
        routingIntegrationBcAPI.saveEventHistory(eventHistoryEntity, token);
    }
}
