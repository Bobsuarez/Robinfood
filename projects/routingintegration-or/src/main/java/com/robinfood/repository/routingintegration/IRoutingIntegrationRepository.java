package com.robinfood.repository.routingintegration;

import com.robinfood.entities.api.routingintegration.ResponseRoutingIntegrationEntity;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

/**
 * Repository Get routing configuration to the different unified order stacks
 */
public interface IRoutingIntegrationRepository {

    /**
     * service repository routing integration
     *
     * @param id    identification channel
     * @param token services token
     * @param uuId  identification order
     * @return ResponseRoutingIntegrationEntity Entity
     */
    ResponseRoutingIntegrationEntity getChannelIntegration(
            @Required Long id,
            @Required String token,
            @Required String uuId
    );
}
