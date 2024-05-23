package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.BrandDTO;

public class BrandDTOMock {

    public static BrandDTO getDataDefault() {

        return BrandDTO.builder()
                .id(1L)
                .name("TEST")
                .build();
    }
}
