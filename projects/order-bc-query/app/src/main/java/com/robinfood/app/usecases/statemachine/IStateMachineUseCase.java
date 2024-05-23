package com.robinfood.app.usecases.statemachine;

import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.entities.StatusEntity;

import java.util.List;

public interface IStateMachineUseCase {

    /**
     * It validate if a change of state is valid
     * @param actualState the state of an Orden
     * @param nextState the state that wants to be change
     * @param stateSystem the states of the system
     * @param idOrder the id of an Orden
     * @return an Order State
     */
    Boolean invoke (
            OrderStateDTO actualState,
            OrderStateDTO nextState,
            List<StatusEntity> stateSystem,
            Long idOrder
            );
}
