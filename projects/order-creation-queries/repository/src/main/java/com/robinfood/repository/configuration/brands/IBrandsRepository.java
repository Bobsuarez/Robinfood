package com.robinfood.repository.configuration.brands;

import com.robinfood.core.dtos.configuration.BrandsDTO;
import com.robinfood.core.enums.Result;

public interface IBrandsRepository {

    /**
     * get the list of brands by companies
     *
     * @param token token auth service
     * @return data of brands companies
     */
    Result<BrandsDTO> getAll(String token);
}
