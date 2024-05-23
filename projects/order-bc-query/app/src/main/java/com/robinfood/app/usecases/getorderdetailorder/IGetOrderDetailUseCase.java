package com.robinfood.app.usecases.getorderdetailorder;

import com.robinfood.core.dtos.GetOrderDetailDTO;

import java.util.List;

public interface IGetOrderDetailUseCase {

    /**
     * Retrieves the list of detail orders DTO
     *
     * @param ids the Id's of orders
     * @return List <GetOrderDetailDTO> the list of order detail order DTO
     */
    List<GetOrderDetailDTO> invoke(List<Long> ids);

}
