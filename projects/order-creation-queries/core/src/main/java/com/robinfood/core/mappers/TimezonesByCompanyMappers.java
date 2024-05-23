package com.robinfood.core.mappers;

import com.robinfood.core.dtos.configuration.TimezonesByCompanyDTO;

import java.util.List;

public final class TimezonesByCompanyMappers {

    private TimezonesByCompanyMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static TimezonesByCompanyDTO listStringToTimezonesByCompanyDTO(List<String> timezones) {
        return TimezonesByCompanyDTO
                .builder()
                .timezones(timezones)
                .build();
    }
}
