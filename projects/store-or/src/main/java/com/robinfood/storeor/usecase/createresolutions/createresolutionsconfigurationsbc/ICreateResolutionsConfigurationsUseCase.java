package com.robinfood.storeor.usecase.createresolutions.createresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Use case that create in resolutions response
 */
public interface ICreateResolutionsConfigurationsUseCase {

    /**
     * Create Resolutions in configurations bc
     *
     * @param storeResolutionsDTO contain the stored resolutions provider response creation
     * @param token               token of service
     * @return object with the basic information of the creation of an resolutions response
     */
    List<ResponseResolutionsWithPosDTO> invoke(
            @NotNull StoreResolutionsDTO storeResolutionsDTO,
            String token
    ) throws ResolutionCrudException;
}
