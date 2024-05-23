package com.robinfood.app.strategies.orderhistoryfilter.chain;

import com.robinfood.app.strategies.orderhistoryfilter.strategies.IOrderHistoryFilterStrategy;
import com.robinfood.app.strategies.orderhistoryfilter.strategies.OrderHistoryFilterChannelIdsNonNullStrategy;
import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Implementation of IOrderHistoryFilterChain
 */
@Component
public class OrderHistoryFilterChannelIdsNonNullChain implements IOrderHistoryFilterChain {

    private final IOrderHistoryFilterChain orderHistoryFilterChain;

    private final IOrderHistoryFilterStrategy orderHistoryFilterStrategy;

    public OrderHistoryFilterChannelIdsNonNullChain(
            OrderHistoryFilterSearchTextNonNullChain orderHistoryFilterChain,
            OrderHistoryFilterChannelIdsNonNullStrategy orderHistoryFilterStrategy
    ) {
        this.orderHistoryFilterChain = orderHistoryFilterChain;
        this.orderHistoryFilterStrategy = orderHistoryFilterStrategy;
    }

    @Override
    public IOrderHistoryFilterStrategy handler(OrderHistoryRequestDTO orderHistoryRequestDTO) {

        if (Objects.nonNull(orderHistoryRequestDTO.getChannelsId()) &&
                Objects.isNull(orderHistoryRequestDTO.getSearchText())
        ) {
            return orderHistoryFilterStrategy;
        }

        return orderHistoryFilterChain.handler(orderHistoryRequestDTO);
    }

}
