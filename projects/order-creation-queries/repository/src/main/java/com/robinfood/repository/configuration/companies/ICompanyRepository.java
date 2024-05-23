package com.robinfood.repository.configuration.companies;

import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.enums.Result;

public interface ICompanyRepository {

    /**
     * Get companies configuration
     *
     * @param token Token auth service
     * @return Result companies when is actives
     */
    Result<CompaniesDTO> getActive(String token);
}
