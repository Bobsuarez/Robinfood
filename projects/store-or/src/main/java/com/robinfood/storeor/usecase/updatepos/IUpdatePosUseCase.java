package com.robinfood.storeor.usecase.updatepos;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;

/**
 * Use case that update information pos response
 */
public interface IUpdatePosUseCase {

    /**
     * Manage update of pos in configurations bc and orders
     *
     * @param posDTO contain the pos response update
     * @param posId  Request id of to be updated
     */
    void invoke(Long posId, PosDTO posDTO) throws PosException;
}
