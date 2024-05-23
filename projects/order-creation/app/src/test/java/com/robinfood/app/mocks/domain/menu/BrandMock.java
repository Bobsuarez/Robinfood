package com.robinfood.app.mocks.domain.menu;

import com.robinfood.core.models.domain.menu.Brand;

public class BrandMock {

    private static final Long ID = 3L;
    private static final Long COUNTRY_ID = 1L;
    private static final Long FRANCHISE_ID = 3L;
    private static final String COLOR = "color_test";
    private static final String NAME = "pixi";

    public static Brand build() {
        return Brand.builder()
            .id(ID)
            .countryId(COUNTRY_ID)
            .color(COLOR)
            .franchiseId(FRANCHISE_ID)
            .name(NAME)
            .build();
    }

}
