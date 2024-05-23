package com.robinfood.app.usecases.getorderdetailbyuuids;

import com.robinfood.core.dtos.GetOrderDetailDTO;

import java.util.List;

/**
 * Get Order Detail By Uuids Use Case
 */
public interface IGetOrderDetailByUuidsUseCase {

    /**
     * Get Order Detail By Uuids
     * @param orderUuids
     * @return Order Detail List
     */
    List<GetOrderDetailDTO> invoke(List<String> orderUuids);

}
