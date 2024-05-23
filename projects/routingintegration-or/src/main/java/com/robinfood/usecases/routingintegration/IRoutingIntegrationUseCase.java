package com.robinfood.usecases.routingintegration;

import com.robinfood.dtos.routinintegration.RoutingIntegrationDTO;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

/**
 * Get routing configuration to the different unified order stacks use case
 */
public interface IRoutingIntegrationUseCase {

    /**
     * use case routing integration
     *
     * @param id    identification channel
     * @param token services token
     * @param uuId  identification Order
     * @return RoutingIntegrationDTO DTO
     */
    RoutingIntegrationDTO invoke(
            @Required Long id,
            @Required String token,
            @Required String uuId
    );
}
