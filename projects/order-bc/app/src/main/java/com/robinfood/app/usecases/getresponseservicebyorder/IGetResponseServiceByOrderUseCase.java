package com.robinfood.app.usecases.getresponseservicebyorder;

import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.entities.OrderServicesEntity;

import java.util.Optional;

public interface IGetResponseServiceByOrderUseCase {

    /**
     * Get service dto
     *
     * @param orderService order service
     * @return ResponseServiceDTO information of service
     */
    Optional<ResponseServiceDTO> invoke(OrderServicesEntity orderService);

}
