package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.repositories.jpa.CityRepository;
import com.robinfood.configurations.samples.CitySample;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void testFindCitiesByCountry() {
        when(
            cityRepository.findCitiesByCountry(1L)
        ).thenReturn(CitySample.getCities());

        assertAll(() -> cityService.findCitiesByCountry(1L));
    }

    @Test
    void test_ListTimeZoneByCompanyCountryId_Should_ReturnListTimeZone_When_RepositoryIsCalled() {
        List<String> listTimeZone = Collections.singletonList("America/Bogota");

        when(cityRepository.listTimeZonesByCompanyCountryId(anyLong()))
            .thenReturn(listTimeZone);

        assertAll(() -> cityService.listTimeZonesByCompanyCountryId(1L));

        verify(cityRepository, times(1)).listTimeZonesByCompanyCountryId(anyLong());

    }
}
