package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderDiscountEntity;
import java.time.LocalDateTime;

public class OrderDiscountEntityMock {

    public static OrderDiscountEntity build() {
        return OrderDiscountEntity.builder()
            .createdAt(LocalDateTime.now())
            .discountId(1L)
            .discountValue(2000.00)
            .id(1L)
            .orderDiscountTypeId(1L)
            .orderId(1L)
            .orderFinalProductId(1L)
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
