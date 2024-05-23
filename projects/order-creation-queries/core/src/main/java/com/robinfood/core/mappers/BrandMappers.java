package com.robinfood.core.mappers;

import com.robinfood.core.dtos.BrandDTO;
import com.robinfood.core.dtos.configuration.StoreMultiBrandDTO;
import com.robinfood.core.dtos.menu.MenuBrandDTO;

public final class BrandMappers {

    private BrandMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static BrandDTO menuBrandDtoToBrandDTO(MenuBrandDTO menuBrandDTO) {
        return BrandDTO.builder()
                .color(menuBrandDTO.getColor())
                .image(menuBrandDTO.getImage())
                .name(menuBrandDTO.getName())
                .build();
    }

    public static BrandDTO multiBrandDtoToBrandDTO(StoreMultiBrandDTO storeMultiBrandDTO) {
        return BrandDTO.builder()
                .color(storeMultiBrandDTO.getColor())
                .image(storeMultiBrandDTO.getImage())
                .name(storeMultiBrandDTO.getName())
                .build();
    }
}
