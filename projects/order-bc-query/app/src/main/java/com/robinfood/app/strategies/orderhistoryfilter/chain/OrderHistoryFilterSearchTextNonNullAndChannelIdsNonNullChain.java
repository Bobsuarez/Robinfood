package com.robinfood.app.strategies.orderhistoryfilter.chain;

import com.robinfood.app.strategies.orderhistoryfilter.strategies.IOrderHistoryFilterStrategy;
import com.robinfood.app.strategies.orderhistoryfilter.strategies.OrderHistoryFilterSearchTextNonNullAndChannelIdsNonNullStrategy;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import org.springframework.stereotype.Component;

/**
 * Implementation of IOrderHistoryFilterChain
 */
@Component
public class OrderHistoryFilterSearchTextNonNullAndChannelIdsNonNullChain implements IOrderHistoryFilterChain {

    private final IOrderHistoryFilterStrategy orderHistoryFilterStrategy;

    public OrderHistoryFilterSearchTextNonNullAndChannelIdsNonNullChain(
            OrderHistoryFilterSearchTextNonNullAndChannelIdsNonNullStrategy orderHistoryFilterStrategy
    ) {
        this.orderHistoryFilterStrategy = orderHistoryFilterStrategy;
    }

    @Override
    public IOrderHistoryFilterStrategy handler(OrderHistoryRequestDTO orderHistoryRequestDTO) {

        return orderHistoryFilterStrategy;
    }

}
