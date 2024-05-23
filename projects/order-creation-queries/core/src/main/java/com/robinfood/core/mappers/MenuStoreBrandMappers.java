package com.robinfood.core.mappers;

import com.robinfood.core.dtos.menu.MenuStoreBrandDTO;
import com.robinfood.core.entities.menu.MenuStoreBrandEntity;

public final class MenuStoreBrandMappers {

    private MenuStoreBrandMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static MenuStoreBrandDTO menuStoreBrandEntityToMenuStoreBrandDTO(MenuStoreBrandEntity menuStoreBrandEntity) {

        return MenuStoreBrandDTO.builder()
                .id(menuStoreBrandEntity.getId())
                .storeId(menuStoreBrandEntity.getStoreId())
                .storeName(menuStoreBrandEntity.getStoreName())
                .build();
    }
}
