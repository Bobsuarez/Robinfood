package com.robinfood.ordereports_bc_muyapp.usecases.getresponseservicebyorder;

import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseServiceDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderServicesEntity;

import java.util.Optional;

public interface IGetResponseServiceByOrderUseCase {

    /**
     * Get service dto
     *
     * @param orderService order service
     *
     * @return ResponseServiceDTO information of service
     */
    Optional<ResponseServiceDTO> invoke(OrderServicesEntity orderService);

}
