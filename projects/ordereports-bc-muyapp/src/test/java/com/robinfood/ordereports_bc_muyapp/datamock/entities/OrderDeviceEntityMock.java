package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderDeviceEntity;

import java.time.LocalDateTime;


public class OrderDeviceEntityMock {

    public static OrderDeviceEntity getDataDefault() {
        return OrderDeviceEntity.builder()
                .ip("120.0.0.1")
                .orderId(1)
                .version("V2")
                .platformId((short) 2)
                .createdAt(LocalDateTime.now())
                .build();
    }
}