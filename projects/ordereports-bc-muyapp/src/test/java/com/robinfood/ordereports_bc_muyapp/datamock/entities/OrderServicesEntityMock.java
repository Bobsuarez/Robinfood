package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderServicesEntity;

import java.time.LocalDateTime;

public class OrderServicesEntityMock {

    public static OrderServicesEntity getDataDefault() {
        return OrderServicesEntity.builder()
                .createdAt(LocalDateTime.now())
                .discount(5.0)
                .id(1001L)
                .orderId(12345)
                .priceNt(100.0)
                .serviceId((short) 1)
                .taxPercentage(10.0)
                .taxPrice(10.0)
                .total(110.0)
                .build();
    }
}
