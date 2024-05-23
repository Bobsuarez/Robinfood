package com.robinfood.app.usecases.getorderdetaildiscountbyorderids;

import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;

import java.util.List;
import java.util.Map;

/**
 * Use case that groups the list of order detail discounts by id
 */
public interface IGroupOrderDetailDiscountByOrderIdsUseCase {

    /**
     * Retrieves the list of order detail discount DTO by order id
     *
     * @param orderDiscountDTOS the list discounts id's
     * @return Map<Long, List <GetOrderDetailDiscountDTO>
     * Long the order id
     * List<GetOrderDetailDiscountDTO> the list of order detail discount DTO
     */
    Map<Long, List<GetOrderDetailDiscountDTO>> invoke(List<OrderDiscountDTO> orderDiscountDTOS);
}
