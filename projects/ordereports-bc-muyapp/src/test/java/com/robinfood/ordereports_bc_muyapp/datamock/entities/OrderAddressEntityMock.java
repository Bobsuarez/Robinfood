package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderAddressEntity;

public class OrderAddressEntityMock {

    public static OrderAddressEntity getDataDefault() {

        return OrderAddressEntity.builder()
                .address("address")
                .cityId((short) 12)
                .countryId((short) 2)
                .latitude("latitude")
                .longitude("longitude")
                .notes("notes")
                .orderId(1)
                .transactionId(123456)
                .zipCode("Zipcode")
                .build();
    }
}