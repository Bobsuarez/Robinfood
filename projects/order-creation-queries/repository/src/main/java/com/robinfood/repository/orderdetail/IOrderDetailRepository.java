package com.robinfood.repository.orderdetail;

import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository for order detail related data
 */
public interface IOrderDetailRepository {

    /**
     * Retrieves the order id's based on the following query params
     * @param orderIds the list of orders by id
     * @param orderUids the list of orders by uid
     * @return the order detail containing the orders detailed info
     */
    Result<List<OrderDetailDTO>> getOrderDetail(
            String token,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );

}
