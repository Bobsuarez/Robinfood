package com.robinfood.app.mocks.configurations;

import com.robinfood.core.dtos.configuration.TimezonesByCompanyDTO;

import java.util.List;

public class TimezonesByCompanyDTOMock {

    public static TimezonesByCompanyDTO getDataDefault() {

        return TimezonesByCompanyDTO.builder()
                .timezones(List.of("America/Bogota"))
                .build();
    }
}
