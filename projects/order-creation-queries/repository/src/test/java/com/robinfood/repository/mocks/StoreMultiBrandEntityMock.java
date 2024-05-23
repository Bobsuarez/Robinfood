package com.robinfood.repository.mocks;

import com.robinfood.core.entities.configurations.StoreMultiBrandEntity;

public final class StoreMultiBrandEntityMock {

    public static StoreMultiBrandEntity getDataDefault() {

        return StoreMultiBrandEntity.builder()
                .color("500EA6")
                .image("https://logo-pos-robinfood.png''")
                .name("RobinFood 27")
                .build();
    }
}
