package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderUserDataEntity;
import java.time.LocalDateTime;

public class OrderUserDataEntityMock {

    public static OrderUserDataEntity build() {
        return OrderUserDataEntity.builder()
            .createdAt(LocalDateTime.now())
            .email("email@test")
            .firstName("FirstName Test")
            .id(1L)
            .lastName("LastName Test")
            .mobile("3007638822")
            .orderId(1L)
            .userId(1L)
            .build();
    }

}
