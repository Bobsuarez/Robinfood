package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.orderhistory.response.OrderHistoryItemDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that gets the order history
 */
public interface IGetOrderHistoryUseCase {

    /**
     * Retrieves the order history based on the following query params
     * @param orderHistoryRequestDTO information for the different filters
     * @return the order history containing the orders detailed info
     */
    Result<EntityDTO<OrderHistoryItemDTO>> invoke(OrderHistoryRequestDTO orderHistoryRequestDTO);
}
