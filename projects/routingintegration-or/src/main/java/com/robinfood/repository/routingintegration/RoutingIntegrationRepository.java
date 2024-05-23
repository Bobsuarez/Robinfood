package com.robinfood.repository.routingintegration;

import com.robinfood.entities.api.routingintegration.ResponseRoutingIntegrationEntity;
import com.robinfood.network.api.RoutingIntegrationBcApi;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

public class RoutingIntegrationRepository implements IRoutingIntegrationRepository {

    private final RoutingIntegrationBcApi routingIntegrationBcApi;

    public RoutingIntegrationRepository() {
        this.routingIntegrationBcApi = new RoutingIntegrationBcApi();
    }

    public RoutingIntegrationRepository(RoutingIntegrationBcApi routingIntegrationBcApi) {
        this.routingIntegrationBcApi = routingIntegrationBcApi;
    }

    @Override
    public ResponseRoutingIntegrationEntity getChannelIntegration(
            @Required Long id,
            @Required String token,
            @Required String uuId
    ) {

        return routingIntegrationBcApi.getChannelIntegration(id, token, uuId);
    }
}
