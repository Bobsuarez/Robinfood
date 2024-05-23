package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderPaymentEntity;
import java.time.LocalDateTime;

public class OrderPaymentEntityMock {

    public static OrderPaymentEntity build() {
        return new OrderPaymentEntity(
            LocalDateTime.now(),
            2000.00,
            1L,
            1L,
            1L,
            1L,
            10000.00,
            0.0,
            LocalDateTime.now(),
            10000.00
        );
    }

}
