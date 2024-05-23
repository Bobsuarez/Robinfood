package com.robinfood.repository.orderdetailprint;

import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository for order detail print related data
 */
public interface IOrderDetailPrintRepository {

    /**
     * Retrieves the order detail based on the following query parameters
     * @param orderIds the list id of orders
     * @param orderUids the list uid of orders
     * @return the order detail containing the orders detailed info
     */
    Result<List<OrderDetailDTO>> getOrderDetailPrint(
            String token,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );
}
