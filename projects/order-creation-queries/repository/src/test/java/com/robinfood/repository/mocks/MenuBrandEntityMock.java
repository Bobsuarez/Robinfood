package com.robinfood.repository.mocks;

import com.robinfood.core.entities.menu.MenuBrandEntity;

public final class MenuBrandEntityMock {

    public static MenuBrandEntity getDataDefault() {
        return MenuBrandEntity.builder()
                .color("color")
                .id(1L)
                .countryId(1L)
                .name("store")
                .storeBrand(MenuStoreBrandEntityMock.getDataDefault())
                .build();
    }
}
