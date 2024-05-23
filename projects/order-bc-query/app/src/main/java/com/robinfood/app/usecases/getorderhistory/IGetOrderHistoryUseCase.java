package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.dtos.response.EntityDTO;

/**
 * Use case that gets the order history
 */
public interface IGetOrderHistoryUseCase {

    /**
     * Retrieves the order history based on the following query params
     * @param orderHistoryRequestDTO information for the different filters
     * @return the order history containing the orders detailed info
     */
    EntityDTO<OrderHistoryItemDTO> invoke(OrderHistoryRequestDTO orderHistoryRequestDTO);
}
