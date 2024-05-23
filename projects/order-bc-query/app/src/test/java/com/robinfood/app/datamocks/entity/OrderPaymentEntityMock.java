package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderPaymentEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderPaymentEntityMock {

    public static OrderPaymentEntity build() {
        return OrderPaymentEntity.builder()
                .createdAt(LocalDateTime.now())
                .discount(2000.00)
                .id(1L)
                .orderId(1L)
                .originId(1L)
                .paymentMethodId(1L)
                .subtotal(10000.00)
                .tax(0.0)
                .updatedAt(LocalDateTime.now())
                .value(10000.00)
                .build();
    }

    public static List<OrderPaymentEntity> buildList() {
        return new ArrayList<>(Arrays.asList(
                OrderPaymentEntity.builder()
                        .createdAt(LocalDateTime.now())
                        .discount(2.0)
                        .id(1L)
                        .orderId(1L)
                        .originId(1L)
                        .paymentMethodId(15L)
                        .subtotal(2.0)
                        .tax(2.0)
                        .updatedAt(LocalDateTime.now())
                        .value(2.0)
                        .build()
        ));

    }

}
