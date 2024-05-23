package com.robinfood.configurations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.models.CompanyBrand;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyBrandService {

    CompanyBrand findById(Long id);

    /**
     * Método que obtiene el listado de todos los países
     *
     * @return List<Country> Listado de países
     */
    List<CompanyBrand> findAll();

    Page<CompanyBrand> list(Long companyId, String name, Long storeId, Pageable pageable)
        throws JsonProcessingException;

    CompanyBrand getByCompanyIdAndMenuBrandId(Long menuBrandId)
            throws JsonProcessingException;
}
