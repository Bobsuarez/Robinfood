package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.CompaniesDTO;

import java.util.List;

public class CompaniesDTOMock {

    public static CompaniesDTO getDataDefault() {
        return CompaniesDTO.builder()
                .companies(List.of(CompanyDTOMock.getDataDefault(), CompanyDTOMock.getDataDefault()))
                .build();
    }
}
