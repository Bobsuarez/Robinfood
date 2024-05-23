package com.robinfood.app.strategies.orderhistoryfilter.chain;

import com.robinfood.app.strategies.orderhistoryfilter.strategies.IOrderHistoryFilterStrategy;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;

/**
 * Chain for get consulting the order history
 */
public interface IOrderHistoryFilterChain {

    /**
     * Get Strategy for consulting the order history
     *
     * @param orderHistoryRequestDTO information for the different filters
     * @return Strategy for consulting the order history
     */
    IOrderHistoryFilterStrategy handler(OrderHistoryRequestDTO orderHistoryRequestDTO);

}
