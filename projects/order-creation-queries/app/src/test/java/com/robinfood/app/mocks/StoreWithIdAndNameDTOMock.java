package com.robinfood.app.mocks;

import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;

public class StoreWithIdAndNameDTOMock {

    public static StoreWithIdAndNameDTO getDataDefault() {

        return StoreWithIdAndNameDTO.builder()
                .id(1L)
                .name("TEST")
                .build();
    }
}
