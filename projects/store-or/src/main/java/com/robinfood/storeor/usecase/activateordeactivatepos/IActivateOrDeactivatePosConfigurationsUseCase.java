package com.robinfood.storeor.usecase.activateordeactivatepos;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import org.jetbrains.annotations.NotNull;

/**
 * Use case that update status in pos response
 */
public interface IActivateOrDeactivatePosConfigurationsUseCase {

    /**
     * Active or Deactivate Pos in configurations bc
     *
     * @param activateOrDeactivatePosDTO contain status of pos for active or deactivate
     * @param posId                      Request id of pos to active or deactivate
     */
    void invoke(
            @NotNull ActivateOrDeactivatePosDTO activateOrDeactivatePosDTO,
            Long posId
    ) throws PosException;
}
