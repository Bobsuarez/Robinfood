package com.robinfood.app.strategies.orderhistoryfilter.strategies;

import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.repository.orders.IOrdersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;

/**
 * Implementation of IOrderHistoryFilterStrategy
 */
@Component
public class OrderHistoryFilterChannelIdsNonNullStrategy implements IOrderHistoryFilterStrategy {

    private final IOrdersRepository ordersRepository;

    public OrderHistoryFilterChannelIdsNonNullStrategy(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Page<OrderEntity> execute(
            List<Long> deliveryTypeIds,
            Map<String, LocalDateTime> localDateTimeMap,
            OrderHistoryRequestDTO orderHistoryRequestDTO
    ) {

        return ordersRepository
                .findAllByOriginIdInAndStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                        orderHistoryRequestDTO.getChannelsId(),
                        orderHistoryRequestDTO.getStoreId(),
                        deliveryTypeIds,
                        ORDER_PAID,
                        localDateTimeMap.get(LOCAL_DATE_TIME_START),
                        localDateTimeMap.get(LOCAL_DATE_TIME_END),
                        PageRequest.of(
                                orderHistoryRequestDTO.getCurrentPage() - DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT,
                                orderHistoryRequestDTO.getPerPage()
                        )
                );
    }

}
