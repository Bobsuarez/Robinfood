package com.robinfood.repository.mocks;

import com.robinfood.core.entities.menu.MenuStoreBrandEntity;

public final class MenuStoreBrandEntityMock {

    public static MenuStoreBrandEntity getDataDefault() {

        return MenuStoreBrandEntity.builder()
                .id(1L)
                .storeId(1L)
                .storeName("store")
                .build();
    }
}
