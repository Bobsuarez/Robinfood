package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.CompanyByStoreDTO;

public class CompanyByStoreDTOMock {

    public static CompanyByStoreDTO getDataDefault() {

        return CompanyByStoreDTO.builder()
                .identification("123")
                .country(CountryByStoreDTOMock.getDataDefault())
                .build();
    }
}
