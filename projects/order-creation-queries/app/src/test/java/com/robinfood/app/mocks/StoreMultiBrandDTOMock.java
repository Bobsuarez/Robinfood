package com.robinfood.app.mocks;

import com.robinfood.core.dtos.configuration.StoreMultiBrandDTO;

public final class StoreMultiBrandDTOMock {

    public static StoreMultiBrandDTO getDataDefault() {

        return StoreMultiBrandDTO.builder()
                .color("500EA6")
                .image("https://logo-pos-robinfood.png''")
                .name("RobinFood 27")
                .build();
    }
}
