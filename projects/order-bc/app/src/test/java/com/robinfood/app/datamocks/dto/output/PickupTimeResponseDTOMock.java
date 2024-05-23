package com.robinfood.app.datamocks.dto.output;

import com.robinfood.core.dtos.pickuptime.PickupTimeResponseDTO;

public class PickupTimeResponseDTOMock {

    private static final Long PICKUP_TIME_ID = 1L;

    public static PickupTimeResponseDTO build() {
        return PickupTimeResponseDTO.builder()
            .id(PICKUP_TIME_ID)
            .build();
    }

}
