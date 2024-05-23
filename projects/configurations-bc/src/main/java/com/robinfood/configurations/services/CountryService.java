package com.robinfood.configurations.services;

import com.robinfood.configurations.models.Country;

import java.util.List;

public interface CountryService {

    /**
     * Método que obtiene el listado de todos los países
     *
     * @return List<Country> Listado de países
     */
    List<Country> findAllCountries();
}
