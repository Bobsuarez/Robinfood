package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.repositories.jpa.CountryRepository;
import com.robinfood.configurations.samples.CountrySample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {
    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryRepository countryRepository;

    @Test
    void testFindAllCountries() {
        when(countryRepository.findAll(any(Sort.class)))
            .thenReturn(CountrySample.getCountries());
        assertAll(() -> countryService.findAllCountries());
    }
}