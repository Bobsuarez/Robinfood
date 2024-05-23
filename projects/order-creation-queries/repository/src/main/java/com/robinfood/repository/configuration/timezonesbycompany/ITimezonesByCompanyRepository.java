package com.robinfood.repository.configuration.timezonesbycompany;

import com.robinfood.core.dtos.configuration.TimezonesByCompanyDTO;
import com.robinfood.core.enums.Result;

public interface ITimezonesByCompanyRepository {

    /**
     * Get timezones by company
     *
     * @param token token auth service
     * @param companyId company id
     * @return Result timezones by company
     */
    Result<TimezonesByCompanyDTO> getTimezonesByCompanyStores(String token, Long companyId);
}
