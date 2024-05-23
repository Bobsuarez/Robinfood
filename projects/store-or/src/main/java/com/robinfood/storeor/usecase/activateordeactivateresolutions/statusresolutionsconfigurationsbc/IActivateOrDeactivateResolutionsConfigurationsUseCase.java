package com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsconfigurationsbc;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that update status in resolutions response
 */
public interface IActivateOrDeactivateResolutionsConfigurationsUseCase {

    /**
     * Active or Deactivate Resolutions in configurations bc
     *
     * @param activateOrDeactivateDTO contain status of resolution for active or deactivate
     * @param resolutionId            Request id of resolutions to active or deactivate
     * @param token                   token of service
     */
    void invoke(
            @NotNull ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId,
            String token
    ) throws ResolutionCrudException;
}
