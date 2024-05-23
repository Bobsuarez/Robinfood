package com.robinfood.app.datamocks.domain;

import com.robinfood.core.models.domain.PickupTime;
import java.util.Collections;

public class PickupTimeMock {

    private static final Long PICKUP_TIME_ID = 1L;

    public static PickupTime build() {
        return PickupTime.builder()
            .id(PICKUP_TIME_ID)
            .stores(Collections.singletonList(
                StoreMock.build())
            )
            .build();
    }

}
