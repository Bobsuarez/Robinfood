package com.robinfood.repository.services;

import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.enums.Result;

/**
 * Repository for services related data
 */
public interface IServiceRepository {

    /**
     * Get service detail by order uid
     * @param orderUId order uid
     * @param token    the authentication token
     * @return the service detail
     */
    Result<ServiceDetailDTO> getServiceDetail(
        String orderUId,
        String token
    );

}
