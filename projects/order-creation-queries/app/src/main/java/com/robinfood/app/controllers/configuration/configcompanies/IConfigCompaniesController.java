package com.robinfood.app.controllers.configuration.configcompanies;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import org.springframework.http.ResponseEntity;

/**
 * Controller that exposes final point in relation to the list companies.
 */
public interface IConfigCompaniesController {

    /**
     * list companies by app
     *
     * @return Return list companies
     */
    ResponseEntity<ApiResponseDTO<CompaniesDTO>> invoke();
}
