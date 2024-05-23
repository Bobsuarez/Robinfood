package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderCouponEntity;

import java.math.BigDecimal;

public class OrderCouponEntityMock {

    public static OrderCouponEntity getDataDefault() {
        return OrderCouponEntity.builder()
                .transactionId(1)
                .id(1)
                .code("code")
                .value(new BigDecimal("1.0"))
                .build();
    }
}
