package com.robinfood.app.mocks;

import com.robinfood.core.dtos.menu.MenuStoreBrandDTO;

public final class MenuStoreBrandDTOMock {

    public static MenuStoreBrandDTO getDataDefault() {

        return MenuStoreBrandDTO.builder()
                .id(1L)
                .storeId(1L)
                .storeName("store")
                .build();
    }
}
