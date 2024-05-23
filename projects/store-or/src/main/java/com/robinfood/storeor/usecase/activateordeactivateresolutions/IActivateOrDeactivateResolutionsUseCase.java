package com.robinfood.storeor.usecase.activateordeactivateresolutions;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that update status of resolutions response
 */
public interface IActivateOrDeactivateResolutionsUseCase {

    /**
     * Manage update status of resolutions in configurations and orders
     *
     * @param activateOrDeactivateDTO contain request for active or deactivate resolutions
     * @param resolutionId            Request id of resolutions to be updated
     */
    void invoke(
            @NotNull ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId) throws ResolutionCrudException;
}
