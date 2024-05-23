package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.CountryByStoreDTO;

public class CountryByStoreDTOMock {

    public static CountryByStoreDTO getDataDefault() {

        return CountryByStoreDTO.builder()
                .id(1L)
                .name("City")
                .build();
    }
}
