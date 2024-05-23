package com.robinfood.storeor.usecase.createresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Use case that create resolutions ids response
 */
public interface ICreateResolutionsUseCase {

    /**
     * Manage creation of resolutions in configurations bc and orders
     *
     * @param storeResolutionsDTO contain the stored resolution provider response creation
     * @return object with the basic information of the creation of an resolutions response
     */
    List<ResponseResolutionsWithPosDTO> invoke(
            @NotNull StoreResolutionsDTO storeResolutionsDTO
    ) throws ResolutionCrudException;
}
