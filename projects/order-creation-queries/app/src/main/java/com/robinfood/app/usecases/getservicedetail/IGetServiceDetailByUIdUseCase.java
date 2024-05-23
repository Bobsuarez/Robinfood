package com.robinfood.app.usecases.getservicedetail;

import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that retrieved the service detail by order uid
 */
public interface IGetServiceDetailByUIdUseCase {

    /**
     * Retrieves the service detail based on the following order uid
     *
     * @param orderUId orderUId
     * @return the service detailed info
     */
    Result<ServiceDetailDTO> invoke(
        String orderUId
    );
}
