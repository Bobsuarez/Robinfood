package com.robinfood.app.factory;

import com.robinfood.core.dtos.state.AbstractState;
import com.robinfood.core.entities.StatusEntity;

import java.util.List;

public interface IStateUseCaseFactory {

    /**
     * Transforms a list of entitys into a list of IState
     *
     * @param listStatusEntities that needs to be transform
     *
     * @return  A list of states in the system
     */
    List<AbstractState> getStates (List<StatusEntity> listStatusEntities);
}
