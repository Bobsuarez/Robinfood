package com.robinfood.configurations.services;

import com.robinfood.configurations.models.City;

import java.util.List;

public interface CityService {

    /**
     * Método que obtiene el listado de ciudades por pais
     *
     * @return List<City> Listado de ciudades
     */
    List<City> findCitiesByCountry(Long countryId);

    List<String> listTimeZonesByCompanyCountryId(Long companyCountryId);
}
