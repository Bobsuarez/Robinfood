package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.OrderAddressDTO;

public class OrderAddressDTOMock {

    public static OrderAddressDTO build() {
        return OrderAddressDTO.builder()
            .address("Address test")
            .cityId(1)
            .countryId(2)
            .latitude("321321321")
            .longitude("43445332")
            .notes("Notes test")
            .orderId(1L)
            .transactionId(2)
            .zipCode("57")
            .build();
    }

}
