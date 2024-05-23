package com.robinfood.app.usecases.getbrands;

import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the list of brands by companies
 */
public interface IGetBrandsUseCase {

    /**
     * retrieve the brands
     *
     * @return object with the list brands
     */
    Result<List<BrandDTO>> invoke();
}
