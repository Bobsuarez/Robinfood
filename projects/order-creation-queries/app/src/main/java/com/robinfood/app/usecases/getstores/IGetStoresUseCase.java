package com.robinfood.app.usecases.getstores;

import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the list of stores by app
 */
public interface IGetStoresUseCase {

    /**
     * retrieve the stores according to the entered criteria
     *
     * @return object with the data
     */
    Result<List<StoreWithIdAndNameDTO>> invoke();
}
