package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.CompanyDTO;

public class CompanyDTOMock {

    public static CompanyDTO getDataDefault() {
        return CompanyDTO.builder()
                .country(CountryDTOMock.getDataDefault())
                .currency_type("COP")
                .currency_symbol("")
                .id(1L)
                .identification("")
                .name("")
                .build();
    }
}
