package com.robinfood.app.mocks;

import com.robinfood.core.dtos.BrandDTO;
import com.robinfood.core.dtos.menu.MenuBrandDTO;

public final class BrandDTOMock {

    public static BrandDTO getDataDefault() {

        return BrandDTO.builder()
                .color("color")
                .image("iamge")
                .name("store")
                .build();
    }

}
