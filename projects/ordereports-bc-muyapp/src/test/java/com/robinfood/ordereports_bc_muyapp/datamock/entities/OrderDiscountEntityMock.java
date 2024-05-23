package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderDiscountEntity;

import java.time.LocalDateTime;

public class OrderDiscountEntityMock {

    public static OrderDiscountEntity getDataDefault() {
        return OrderDiscountEntity.builder()
                .createdAt(LocalDateTime.now())
                .discountId(1L)
                .discountValue(2000.00)
                .id(1L)
                .orderDiscountTypeId((short) 1)
                .orderId(1)
                .orderFinalProductId(1L)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
