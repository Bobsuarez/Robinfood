package com.robinfood.storeor.usecase.updateresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that update information resolutions response
 */
public interface IUpdateResolutionsUseCase {

    /**
     * Manage update of resolutions in configurations bc and orders
     *
     * @param resolutionDTO contain the stored resolution provider response update
     * @param resolutionId  Request id of resolutions to be updated
     */
    void invoke(
            @NotNull ResolutionDTO resolutionDTO,
            Long resolutionId) throws ResolutionCrudException;
}
