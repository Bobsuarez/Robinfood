package com.robinfood.app.usecases.getorderdetailfinalproductportion;

import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;

import java.util.List;
import java.util.Map;

public interface IGetOrderDetailPortionsByProductIdsUseCase {

    /**
     * Retrieves the list of order final product portion DTO by product id
     *
     * @param orderFinalProductIds the list order final product id's
     * @return Map<Long, List <GetOrderDetailFinalProductPortionDTO>
     * Long the order final product id
     * List<GetOrderDetailFinalProductPortionDTO> the list of order final product portion DTO
     */
    Map<Long, List<GetOrderDetailFinalProductPortionDTO>> invoke(List<Long> orderFinalProductIds);
}
