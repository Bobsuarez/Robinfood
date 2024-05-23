package com.robinfood.app.datamocks.dto.input.pickuptime;

import com.robinfood.core.dtos.pickuptime.PickupTimeDTO;
import java.util.Collections;

public class PickupTimeDTOMock {

    public static PickupTimeDTO build() {
        return PickupTimeDTO.builder()
            .stores(Collections.singletonList(StoreDTOMock.build()))
            .build();
    }

}
