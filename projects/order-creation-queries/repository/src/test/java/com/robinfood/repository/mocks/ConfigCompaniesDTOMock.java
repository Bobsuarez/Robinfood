package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.configuration.CompaniesDTO;

import java.util.List;

public class ConfigCompaniesDTOMock {

    public static CompaniesDTO getDataDefault() {
        return CompaniesDTO.builder()
                .companies(List.of(CompanyDTO.builder()
                        .currency_type("")
                        .currency_symbol("")
                        .id(0L)
                        .identification("")
                        .name("")
                        .build()))
                .build();
    }
}