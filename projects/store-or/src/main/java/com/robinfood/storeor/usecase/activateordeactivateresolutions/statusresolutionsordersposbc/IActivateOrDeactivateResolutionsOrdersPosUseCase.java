package com.robinfood.storeor.usecase.activateordeactivateresolutions.statusresolutionsordersposbc;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that update status resolutions in orders in response
 */
public interface IActivateOrDeactivateResolutionsOrdersPosUseCase {

    /**
     * @param activateOrDeactivateDTO request with status resolution in orders
     * @param resolutionId            request id resolution to active or deactivate
     * @param token                   token of service
     */
    void invoke(
            @NotNull ActivateOrDeactivateDTO activateOrDeactivateDTO,
            Long resolutionId,
            String token
    ) throws ResolutionCrudException;
}
