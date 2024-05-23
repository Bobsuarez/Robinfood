package com.robinfood.configurations.utils;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.dto.v1.models.CityDTOResponse;
import com.robinfood.configurations.models.City;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class CityUtil {

    private CityUtil() {
        // CityUtil constructor
    }

    @BasicLog
    public static CityDTOResponse buildCityDTO(City city) {
        log.info("Generating mappedStore for City {}", JsonUtils.convertToJson(city));

        return CityDTOResponse.builder()
            .id(city.getId())
            .name(city.getName())
            .timezone(city.getTimezone())
            .state(
                StateUtil.buildStateDTO(
                    city.getState()
                )
            )
            .build();
    }

    @BasicLog
    public static List<CityDTOResponse> buildCitiesDTO(List<City> cities) {
        log.info("Generating mappedStore for City list {}", JsonUtils.convertToJson(cities));

        return cities.stream()
            .map(CityUtil::buildCityDTO)
            .collect(Collectors.toList());
    }

}
