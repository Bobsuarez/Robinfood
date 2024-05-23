package com.robinfood.app.datamocks.dto.input.pickuptime;

import com.robinfood.core.dtos.pickuptime.StoreDTO;

public class StoreDTOMock {

    public static StoreDTO build() {
        return StoreDTO.builder()
            .id(123L)
            .pickupTime(321321L)
            .build();
    }

}
