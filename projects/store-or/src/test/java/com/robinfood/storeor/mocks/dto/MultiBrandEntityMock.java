package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.entities.StoreMultiBrandEntity;

public final class MultiBrandEntityMock {

    public static StoreMultiBrandEntity getDataDefault() {

        return StoreMultiBrandEntity.builder()
                .image("robinfood.png")
                .color("500EA6")
                .build();
    }
}