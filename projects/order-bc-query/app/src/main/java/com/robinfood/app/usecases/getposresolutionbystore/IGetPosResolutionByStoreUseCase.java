package com.robinfood.app.usecases.getposresolutionbystore;

import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;

import java.util.List;

/**
 * Use case that returns the resolution and the orders effectiveness and cancelled
 */
public interface IGetPosResolutionByStoreUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @param dataRequestDTO Dto that contains the data entered
     * @return Dto request
     */
    List<GetPosResolutionsDTO> invoke(DataPosResolutionRequestDTO dataRequestDTO);
}
