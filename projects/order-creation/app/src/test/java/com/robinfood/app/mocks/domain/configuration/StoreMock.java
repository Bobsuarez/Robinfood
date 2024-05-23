package com.robinfood.app.mocks.domain.configuration;

import com.robinfood.core.models.domain.configuration.Store;

public class StoreMock {

    private static final Long STORE_ID = 1L;
    private static final Long POS_ID = 50L;

    public static Store build(){
        return Store.builder()
            .id(STORE_ID)
            .posId(POS_ID)
            .build();
    }

}
