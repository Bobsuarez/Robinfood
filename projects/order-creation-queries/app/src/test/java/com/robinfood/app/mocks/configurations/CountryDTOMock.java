package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.CountryDTO;

public class CountryDTOMock {

    public static CountryDTO getDataDefault() {

        return CountryDTO.builder()
                .id(1L)
                .name("test")
                .build();
    }
}
