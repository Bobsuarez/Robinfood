package com.robinfood.app.usecases.getorderdetailfinalproductgroup;

import com.robinfood.core.dtos.GetOrderDetailFinalProductGroupDTO;

import java.util.List;
import java.util.Map;

public interface IGetOrderDetailGroupWithPortionsByProductIdsUseCase {

    /**
     * Retrieves the list of order final product group DTO by group id
     *
     * @param orderFinalProductIds the list order final product id's
     * @return Map<Long, List <GetOrderDetailFinalProductGroupDTO>
     * Long the get final product id
     * List<GetOrderDetailFinalProductGroupDTO> the list of order detail groups with portions
     */
    Map<Long, List<GetOrderDetailFinalProductGroupDTO>> invoke(
            List<Long> orderFinalProductIds
    );

}
