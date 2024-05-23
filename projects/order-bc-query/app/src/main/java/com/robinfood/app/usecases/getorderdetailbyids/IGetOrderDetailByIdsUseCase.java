package com.robinfood.app.usecases.getorderdetailbyids;

import com.robinfood.core.dtos.OrderDetailDTO;

import java.util.List;

/**
 * Use case that returns the order detail for some order ids
 */
public interface IGetOrderDetailByIdsUseCase {

    /**
     * Gets the order detail for some order ids
     * @param orderId the list of order id
     * @return the order detail for some order ids
     */
    List<OrderDetailDTO> invoke(List<Long> orderId);
}
