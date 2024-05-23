package com.robinfood.app.strategies.orderhistoryfilter.chain;

import com.robinfood.app.strategies.orderhistoryfilter.strategies.IOrderHistoryFilterStrategy;
import com.robinfood.app.strategies.orderhistoryfilter.strategies.OrderHistoryFilterSearchTextNonNullStrategy;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Implementation of IOrderHistoryFilterChain
 */
@Component
public class OrderHistoryFilterSearchTextNonNullChain implements IOrderHistoryFilterChain {

    private final IOrderHistoryFilterChain orderHistoryFilterSearchTextNonNullAndChannelIdsNonNullChain;
    private final OrderHistoryFilterSearchTextNonNullStrategy orderHistoryFilterSearchTextNonNullStrategy;

    public OrderHistoryFilterSearchTextNonNullChain(
            IOrderHistoryFilterChain orderHistoryFilterSearchTextNonNullAndChannelIdsNonNullChain,
            OrderHistoryFilterSearchTextNonNullStrategy orderHistoryFilterSearchTextNonNullStrategy
    ) {
        this.orderHistoryFilterSearchTextNonNullAndChannelIdsNonNullChain =
                orderHistoryFilterSearchTextNonNullAndChannelIdsNonNullChain;
        this.orderHistoryFilterSearchTextNonNullStrategy = orderHistoryFilterSearchTextNonNullStrategy;
    }

    @Override
    public IOrderHistoryFilterStrategy handler(OrderHistoryRequestDTO orderHistoryRequestDTO) {

        if (Objects.isNull(orderHistoryRequestDTO.getChannelsId()) &&
                Objects.nonNull(orderHistoryRequestDTO.getSearchText())
        ) {
            return orderHistoryFilterSearchTextNonNullStrategy;
        }

        return orderHistoryFilterSearchTextNonNullAndChannelIdsNonNullChain.handler(orderHistoryRequestDTO);
    }

}
