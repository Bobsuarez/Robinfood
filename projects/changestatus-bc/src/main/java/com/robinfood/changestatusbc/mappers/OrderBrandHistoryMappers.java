package com.robinfood.changestatusbc.mappers;

import com.robinfood.changestatusbc.dtos.OrderBrandHistoryDTO;
import com.robinfood.changestatusbc.entities.OrderBrandHistoryEntity;

public final class OrderBrandHistoryMappers {

    private OrderBrandHistoryMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderBrandHistoryEntity toOrderBrandHistoryEntity(OrderBrandHistoryDTO orderBrandHistoryDTO) {
        return OrderBrandHistoryEntity.builder()
                .brandId(orderBrandHistoryDTO.getBrandId())
                .orderId(orderBrandHistoryDTO.getOrderId())
                .orderStatusId(orderBrandHistoryDTO.getOrderStatusId())
                .userId(orderBrandHistoryDTO.getUserId())
                .build();
    }
}
