package com.robinfood.storeor.usecase.updateresolutions.updateresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that update resolutions in orders in response
 */
public interface IUpdateResolutionsOrdersPosUseCase {

    /**
     * @param resolutionDTO response with ids created in configurations bc
     * @param token         token of service
     * @param resolutionDTO contain the stored resolution provider response update
     */
    void invoke(
            @NotNull ResolutionDTO resolutionDTO,
            Long resolutionId,
            String token
    ) throws ResolutionCrudException;
}
