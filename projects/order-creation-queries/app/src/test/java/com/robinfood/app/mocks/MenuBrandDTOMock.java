package com.robinfood.app.mocks;

import com.robinfood.core.dtos.menu.MenuBrandDTO;
import com.robinfood.core.dtos.menu.MenuStoreBrandDTO;

public final class MenuBrandDTOMock {

    public static MenuBrandDTO getDataDefault() {

        return MenuBrandDTO.builder()
                .color("color")
                .id(1L)
                .franchiseId(1L)
                .countryId(1L)
                .name("store")
                .storeBrand(MenuStoreBrandDTOMock.getDataDefault())
                .build();
    }

}
