package com.robinfood.repository.mocks.domain;

import com.robinfood.core.models.domain.pickuptime.Brand;

public class BrandMock {

    private static final Long ID = 321L;
    private static final Long PRINT_TIME = 4213213L;

    public static Brand build() {
        return Brand.builder()
            .id(ID)
            .printTime(PRINT_TIME)
            .build();
    }

}
