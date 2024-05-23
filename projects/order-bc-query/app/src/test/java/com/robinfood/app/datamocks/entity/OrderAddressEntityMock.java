package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderAddressEntity;
import java.time.LocalDateTime;

public class OrderAddressEntityMock {

    public static OrderAddressEntity build() {
        return OrderAddressEntity.builder()
            .address("Address test")
            .cityId(1)
            .countryId(1)
            .latitude("32321321")
            .longitude("23243421")
            .notes("Notes test")
            .orderId(1L)
            .transactionId(1)
            .zipCode("57")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

}
