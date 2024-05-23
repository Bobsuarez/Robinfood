package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderHistoryEntity;

import java.time.LocalDateTime;

public class OrderHistoryEntityMock {

    public static OrderHistoryEntity getDataDefault() {

        return OrderHistoryEntity.builder()
                .orderId(1234567)
                .id(1123L)
                .date(LocalDateTime.now())
                .orderStatusId((short) 1)
                .createdAt(LocalDateTime.now())
                .observation("Test mock")
                .userId(123L)
                .build();

    }
}