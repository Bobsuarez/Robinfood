package com.robinfood.storeor.usecase.createresolutions.createresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Use case that create resolutions in orders in response
 */
public interface ICreateResolutionsOrdersPosUseCase {

    /**
     * @param responseResolutionsWithPosDTOs response with ids created in configurations bc
     * @param token                          token of service
     * @param storeResolutionsDTO            contain the stored resolutions provider response creation
     */
    void invoke(
            List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOs,
            @NotNull StoreResolutionsDTO storeResolutionsDTO,
            String token
    ) throws ResolutionCrudException;
}
