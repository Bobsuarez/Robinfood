package com.robinfood.configurations.utils;

import com.robinfood.configurations.dto.v1.models.CountryDTO;
import com.robinfood.configurations.samples.CountrySample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CountryUtilTest {

    @Test
    void testBuildCountryDTO() {
        CountryDTO country = CountryUtil.buildCountryDTO(
            CountrySample.getCountry());
        Assertions.assertNotNull(country);
    }

    @Test
    void testBuildCountriesDTO() {
        List<CountryDTO> countries = CountryUtil.buildCountriesDTO(
            CountrySample.getCountries());
        Assertions.assertNotNull(countries);
    }
}