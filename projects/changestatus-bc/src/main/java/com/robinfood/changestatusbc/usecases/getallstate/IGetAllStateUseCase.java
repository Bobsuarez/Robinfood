package com.robinfood.changestatusbc.usecases.getallstate;

import com.robinfood.changestatusbc.entities.StatusEntity;

import java.util.List;

/**
 *
 */
public interface IGetAllStateUseCase {

    /**
     * Retrieves all states
     *
     * @return an Order State
     */
    List<StatusEntity> invoke();
}
