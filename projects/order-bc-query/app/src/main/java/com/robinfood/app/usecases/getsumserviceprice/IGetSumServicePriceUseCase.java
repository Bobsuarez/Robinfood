package com.robinfood.app.usecases.getsumserviceprice;

import com.robinfood.core.dtos.request.order.ServiceDTO;

import java.util.List;

/**
 * Use case that gets sum of the order services
 */
public interface IGetSumServicePriceUseCase {

    /**
     * Retrieves order services price
     * @param serviceDTOS
     * @return the sum of the order services
     */
    Double invoke(List<ServiceDTO> serviceDTOS);
}
