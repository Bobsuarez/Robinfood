package com.robinfood.app.usecases.getactivecompanies;

import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that returns the list of companies
 */
public interface IGetActiveCompaniesUseCase {

    /**
     * retrieve the companies
     *
     * @return object with the data
     */
    Result<CompaniesDTO> invoke();
}
