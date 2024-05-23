package com.robinfood.app.usecases.getposresolutionbystore;

import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the resolution and effective and canceled orders by store
 */
public interface IGetPosResolutionByStoreUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @param dataRequestDTO Dto that contains the data entered
     * @return the list of resolutions by store
     */
    Result<List<GetPosResolutionsDTO>> invoke(DataPosResolutionRequestDTO dataRequestDTO);
}
