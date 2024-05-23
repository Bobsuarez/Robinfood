package com.robinfood.app.usecases.getstoresbycountryid;

import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the list of stores by app
 */
public interface IGetStoresByCountryIdUseCase {

    /**
     * retrieve the stores according to the entered criteria
     *
     * @param countryId identifier countryId
     *
     * @return object with the data
     */
    Result<List<StoreWithIdAndNameDTO>> invoke(Long countryId);
}
