package com.robinfood.configurations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.models.Brand;
import com.robinfood.configurations.models.CompanyBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {

    /**
     * Método que consulta la marcas por compañia
     *
     * @return objeto de marca por compañia
     */
    CompanyBrand getByBrandIdAndCompanyId(Long brandId, Long companyCountryId);

    /**
     * Método para consultar las marcas
     *
     * @return Lista de marcas
     */
    Page<Brand> list(Pageable pageable, Boolean enabled) throws JsonProcessingException;

    /**
     * Método usado para contar el total de marcas
     *
     * @return cantidad de marcas
     */
    long count();

}
