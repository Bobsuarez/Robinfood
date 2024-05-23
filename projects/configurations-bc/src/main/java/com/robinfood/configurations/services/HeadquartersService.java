package com.robinfood.configurations.services;

import com.robinfood.configurations.models.Headquarter;

public interface HeadquartersService {

    /**
     * Método que consulta la informacion de la compañia por pais
     *
     * @return objeto de headquarters por compañia pais
     */
    Headquarter getByCompanyCountryId(Long companyCountryId);
}
