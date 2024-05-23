package com.robinfood.changestatusbc.usecases.statemachine;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.entities.StatusEntity;

import java.util.List;

/**
 *
 */
public interface IStateMachineUseCase {

    /**
     * Validate if a change of state is valid
     *
     * @param actualState the state of an order
     * @param nextState the state that wants to be change
     * @param stateSystem the states of the system
     * @param idOrder the id of an order
     * @return an Order State
     */
    Boolean invoke (
            OrderStateDTO actualState,
            OrderStateDTO nextState,
            List<StatusEntity> stateSystem,
            Long idOrder
    );
}
