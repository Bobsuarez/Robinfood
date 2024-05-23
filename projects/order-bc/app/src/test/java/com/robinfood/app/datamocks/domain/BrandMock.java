package com.robinfood.app.datamocks.domain;

import com.robinfood.core.models.domain.Brand;

public class BrandMock {

    private static final Long ID = 3232L;
    private static final Long PRINT_TIME = 24244L;

    public static Brand build() {
        return Brand.builder()
            .id(ID)
            .printTime(PRINT_TIME)
            .build();
    }

}
