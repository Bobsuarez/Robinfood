package com.robinfood.app.mocks.domain;

import com.robinfood.core.models.domain.pickuptime.Store;

public class StoreMock {

    private static final Long STORE_ID = 3211L;
    private static final Long PICKUP_TIME = 14342L;

    public static Store build() {
        return Store.builder()
            .id(STORE_ID)
            .pickupTime(PICKUP_TIME)
            .build();
    }

}
