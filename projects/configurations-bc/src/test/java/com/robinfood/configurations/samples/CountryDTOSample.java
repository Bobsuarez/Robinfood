package com.robinfood.configurations.samples;

import com.robinfood.configurations.dto.v1.models.CountryDTO;

import java.util.List;

public class CountryDTOSample {

    public static CountryDTO getCountryDTO() {
        return CountryDTO.builder()
            .id(1L)
            .name("Colombia")
            .build();
    }

    public static List<CountryDTO> getCountriesDTO() {
        return List.of(
            getCountryDTO(),
            getCountryDTO(),
            getCountryDTO()
        );
    }
}
