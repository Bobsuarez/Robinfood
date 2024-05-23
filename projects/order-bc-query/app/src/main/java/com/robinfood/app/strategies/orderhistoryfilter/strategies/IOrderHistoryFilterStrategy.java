package com.robinfood.app.strategies.orderhistoryfilter.strategies;

import com.robinfood.core.dtos.request.orderhistory.OrderHistoryRequestDTO;
import com.robinfood.core.entities.OrderEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Get paged order history information filter
 */
public interface IOrderHistoryFilterStrategy {

    /**
     * Get order history information filter
     *
     * @param deliveryTypeIds delivery Types
     * @param localDateTimeMap Map Local Date information
     * @param orderHistoryRequestDTO information for the different filters
     * @return Paged order information
     */
    Page<OrderEntity> execute(
            List<Long> deliveryTypeIds,
            Map<String, LocalDateTime> localDateTimeMap,
            OrderHistoryRequestDTO orderHistoryRequestDTO
    );

}
