package com.robinfood.storeor.usecase.updateconfigurationbypos;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;

/**
 * Use case that update in Pos response
 */
public interface IUpdatePosConfigurationsUseCase {

    /**
     * Manage update of pos in configurations bc and orders
     *
     * @param posDTO contain the data pos response update
     * @param posId  Request id of pos  to be updated
     */
    void invoke(Long posId, PosDTO posDTO, String token) throws PosException;
}
