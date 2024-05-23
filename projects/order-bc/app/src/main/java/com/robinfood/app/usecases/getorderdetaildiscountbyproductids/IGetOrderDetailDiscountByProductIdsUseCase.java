package com.robinfood.app.usecases.getorderdetaildiscountbyproductids;

import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;

import java.util.List;
import java.util.Map;

public interface IGetOrderDetailDiscountByProductIdsUseCase {

    /**
     * Retrieves the list of order detail discount DTO by product id
     *
     * @param orderDiscountDTOS the list discounts id's
     * @return Map<Long, List <GetOrderDetailDiscountDTO>
     * Long the product id
     * List<GetOrderDetailDiscountDTO> the list of order detail discount DTO
     */
    Map<Long, List<GetOrderDetailDiscountDTO>> invoke(List<OrderDiscountDTO> orderDiscountDTOS);
}
