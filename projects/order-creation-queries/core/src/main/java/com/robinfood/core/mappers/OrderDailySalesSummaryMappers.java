package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.entities.OrderDailySaleSummaryEntity;

public final class OrderDailySalesSummaryMappers {

    private OrderDailySalesSummaryMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderDailySaleSummaryDTO orderDailySaleSummaryEntityToDTO(
            OrderDailySaleSummaryEntity dailySaleSummaryEntity
    ) {
        return  OrderDailySaleSummaryDTO.builder()
            .salesSummary(dailySaleSummaryEntity.getSalesSummary())
            .ordersNumber(dailySaleSummaryEntity.getOrdersNumber())
            .build();
    }
}
