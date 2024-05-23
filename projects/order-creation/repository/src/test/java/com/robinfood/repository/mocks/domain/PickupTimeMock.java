package com.robinfood.repository.mocks.domain;

import com.robinfood.core.models.domain.pickuptime.PickupTime;
import java.util.Collections;

public class PickupTimeMock {

    public static PickupTime build() {
        return PickupTime.builder()
            .stores(Collections.singletonList(StoreMock.build()))
            .build();
    }

}
