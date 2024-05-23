package com.robinfood.app.usecases.getposresolution;

import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that returns the resolution and the orders effectiveness and cancelled
 */
public interface IGetPosResolutionUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @param dataRequestDTO Dto that contains the data entered
     * @return object with the data pos resolution
     */
    Result<GetPosResolutionsDTO> invoke(DataPosResolutionRequestDTO dataRequestDTO);
}
