package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderPaymentEntity;

import java.time.LocalDateTime;

public class OrderPaymentEntityMock {

    public static OrderPaymentEntity getDataDefault() {
        return OrderPaymentEntity.builder()
                .createdAt(LocalDateTime.now())
                .discount(5.0)
                .id(1001L)
                .orderId(12345)
                .originId((short) 1)
                .paymentMethodId((short) 2)
                .subtotal(100.0)
                .tax(10.0)
                .updatedAt(LocalDateTime.now())
                .value(110.0)
                .build();
    }

}
