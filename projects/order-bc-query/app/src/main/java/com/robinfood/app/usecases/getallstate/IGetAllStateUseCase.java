package com.robinfood.app.usecases.getallstate;

import com.robinfood.core.entities.StatusEntity;

import java.util.List;

public interface IGetAllStateUseCase {

    /**
     * Retrieves all states
     * @return an Order State
     */
    List<StatusEntity> invoke();
}
