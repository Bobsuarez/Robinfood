package com.robinfood.repository.posresolution;

import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository for resolution invoice data
 */
public interface IPosResolutionRepository {

    /**
     * Get object with resolution invoice data
     *
     * @param dataPosResolutionRequestDTO information for the different filters
     * @param token token auth service
     * @return data effective and canceled
     */
    Result<GetPosResolutionsDTO> getDataPosResolution(
            DataPosResolutionRequestDTO dataPosResolutionRequestDTO,
            String token
    );

    /**
     * Get list of object with resolution invoice data
     *
     * @param dataPosResolutionRequestDTO information for the different filters
     * @param token token auth service
     * @return data in list of resolutions of each cashier and by store
     */
    Result<List<GetPosResolutionsDTO>> getDataPosResolutionByStore(
            DataPosResolutionRequestDTO dataPosResolutionRequestDTO,
            String token
    );

}
