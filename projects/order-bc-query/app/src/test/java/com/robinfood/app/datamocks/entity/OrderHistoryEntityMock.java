package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderHistoryEntity;

import java.time.LocalDateTime;
import java.util.List;

public class OrderHistoryEntityMock {

    public static OrderHistoryEntity getDataDefault() {
        return OrderHistoryEntity.builder()
                .createdAt(LocalDateTime.now())
                .id(1L)
                .orderStatusId(1L)
                .orderId(1L)
                .observation("Test")
                .userId(1L)
                .build();
    }

    public static List<OrderHistoryEntity> getDataDefaultList() {
        return List.of(getDataDefault());
    }
}
