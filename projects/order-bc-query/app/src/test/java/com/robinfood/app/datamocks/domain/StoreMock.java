package com.robinfood.app.datamocks.domain;

import com.robinfood.core.models.domain.Store;
import java.util.Collections;

public class StoreMock {

    private static final Long ID = 3221L;
    private static final Long PICKUP_TIME = 3832823L;

    public static Store build() {
        return Store.builder()
            .id(ID)
            .pickuptime(PICKUP_TIME)
            .brands(Collections.singletonList(BrandMock.build()))
            .build();
    }
}
