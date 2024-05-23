package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.repositories.jpa.CityRepository;
import com.robinfood.configurations.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(
        CityRepository cityRepository
    ) {
        this.cityRepository = cityRepository;
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public List<City> findCitiesByCountry(Long countryId) {
        return cityRepository.findCitiesByCountry(countryId);
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public List<String> listTimeZonesByCompanyCountryId(Long companyCountryId) {
        return cityRepository.listTimeZonesByCompanyCountryId(companyCountryId);
    }
}
