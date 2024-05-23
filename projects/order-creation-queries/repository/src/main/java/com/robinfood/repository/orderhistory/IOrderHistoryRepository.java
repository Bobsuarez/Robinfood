package com.robinfood.repository.orderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.orderhistory.response.OrderHistoryItemDTO;
import com.robinfood.core.enums.Result;

/**
 * Repository for order history related data
 */
public interface IOrderHistoryRepository {

    /**
     * Get list order history
     *
     * @param orderHistoryRequestDTO information for the different filters
     * @param token token auth service
     * @return the order history containing the orders detailed info
     */
    Result<EntityDTO<OrderHistoryItemDTO>> getOrderHistory(
            OrderHistoryRequestDTO orderHistoryRequestDTO,
            String token
    );

}
