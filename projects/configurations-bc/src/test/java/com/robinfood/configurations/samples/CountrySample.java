package com.robinfood.configurations.samples;

import com.robinfood.configurations.models.Country;

import java.util.List;

public class CountrySample {

    public static Country getCountry() {
        return Country.builder()
            .id(1L)
            .name("Colombia")
            .build();
    }

    public static List<Country> getCountries() {
        return List.of(
            getCountry(),
            getCountry(),
            getCountry()
        );
    }
}
