package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.configuration.BrandDTO;

public class BrandsDTOMock {

    public static BrandDTO getDataDefault() {

        return BrandDTO.builder()
                .id(1L)
                .name("TEST")
                .build();
    }
}
