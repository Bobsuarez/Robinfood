package com.robinfood.repository.mocks.menu;

import com.robinfood.core.models.retrofit.menu.brand.BrandResponse;

public class BrandResponseMock {

    private static final Long ID = 3L;
    private static final Long COUNTRY_ID = 1L;
    private static final Long FRANCHISE_ID = 3L;
    private static final String COLOR = "color_test";
    private static final String NAME = "pixi";

    public static BrandResponse build() {
        return BrandResponse.builder()
            .id(ID)
            .color(COLOR)
            .countryId(COUNTRY_ID)
            .franchiseId(FRANCHISE_ID)
            .name(NAME)
            .build();
    }

}
