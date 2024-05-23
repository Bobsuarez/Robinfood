package com.robinfood.app.usecases.getdetailorderbyuids;

import com.robinfood.core.dtos.GetOrderDetailDTO;

import java.util.List;

/**
 * Use case that retrieves the menu
 */
public interface IGetOrderDetailByUidsUseCase {

    /**
     * Retrieve the menu for the next query parameter
     * @param uids the uid's of orders
     * @return List <GetOrderDetailDTO> the list of order detail order DTO
     */
    List<GetOrderDetailDTO> invoke(List<String> uids);
}
