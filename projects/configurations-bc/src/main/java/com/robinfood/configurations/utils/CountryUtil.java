package com.robinfood.configurations.utils;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.models.CountryDTO;
import com.robinfood.configurations.models.Country;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class CountryUtil {

    private CountryUtil() {
        // CountryUtil constructor
    }

    @BasicLog
    public static CountryDTO buildCountryDTO(Country country) {
        log.info("Generating mappedStore for Country {}", JsonUtils.convertToJson(country));

        return CountryDTO.builder()
            .id(country.getId())
            .name(country.getName())
            .build();
    }

    @BasicLog
    public static List<CountryDTO> buildCountriesDTO(List<Country> countries) {
        log.info("Generating mappedStore for Country list {}", JsonUtils.convertToJson(countries));

        return countries.stream()
            .map(country -> CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .build())
            .collect(Collectors.toList());
    }
}
