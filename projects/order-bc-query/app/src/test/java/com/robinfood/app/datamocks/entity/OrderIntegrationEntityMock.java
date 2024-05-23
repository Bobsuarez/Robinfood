package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderIntegrationEntity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class OrderIntegrationEntityMock {

    public static OrderIntegrationEntity getDefault() {
        return OrderIntegrationEntity.builder()
                .addressCity("123")
                .addressDescription("Customer")
                .arrivalDate(new Date(1672549200000L))
                .arrivalTime(new Time(1672549200000L))
                .code("123")
                .createdAt(LocalDateTime.now())
                .franchiseId(1L)
                .franchiseName("Customer")
                .integrationId("123")
                .notes("Customer")
                .orderId(1L)
                .originId(1L)
                .originName("Customer")
                .orderType(1)
                .paymentMethod("payment")
                .storeId(1L)
                .storeName("Customer")
                .totalDelivery(2.0)
                .totalDiscount(2.0)
                .totalOrder(1.0)
                .totalTip(2.0)
                .updatedAt(LocalDateTime.now())
                .userName("Customer")
                .userPhone("3054268")
                .build();
    }

}
