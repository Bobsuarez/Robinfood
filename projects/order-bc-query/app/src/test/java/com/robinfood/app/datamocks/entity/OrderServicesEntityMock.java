package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderServicesEntity;

import java.time.LocalDateTime;

public class OrderServicesEntityMock {

    public static OrderServicesEntity getDataDefault() {

        return OrderServicesEntity.builder()
                .createdAt(LocalDateTime.now())
                .discount(0.0)
                .id(1L)
                .orderId(12L)
                .priceNt(1000.0)
                .serviceId(123L)
                .taxPercentage(0.0)
                .taxPrice(0.0)
                .total(1000.0)
                .build();
    }
}

