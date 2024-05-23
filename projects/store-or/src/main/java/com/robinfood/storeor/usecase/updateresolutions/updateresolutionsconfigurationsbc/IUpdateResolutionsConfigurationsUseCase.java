package com.robinfood.storeor.usecase.updateresolutions.updateresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that update in resolutions response
 */
public interface IUpdateResolutionsConfigurationsUseCase {

    /**
     * Update Resolutions in configurations bc
     *
     * @param resolutionDTO contain the stored resolution provider response update
     * @param resolutionId  Request id of resolutions to be updated
     * @param token         token of service
     */
    void invoke(
            @NotNull ResolutionDTO resolutionDTO,
            Long resolutionId,
            String token
    ) throws ResolutionCrudException;
}
