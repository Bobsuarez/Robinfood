package com.robinfood.changestatusbc.mappers;

import com.robinfood.changestatusbc.dtos.OrderHistoryDTO;
import com.robinfood.changestatusbc.entities.OrderHistoryEntity;

public final class OrderHistoryMappers {

    private OrderHistoryMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderHistoryEntity toOrderHistoryEntity(OrderHistoryDTO orderHistoryDTO) {
        return OrderHistoryEntity.builder()
                .observation(orderHistoryDTO.getObservation())
                .orderId(orderHistoryDTO.getOrderId())
                .orderStatusId(orderHistoryDTO.getOrderStatusId())
                .userId(orderHistoryDTO.getUserId())
                .build();
    }
}
