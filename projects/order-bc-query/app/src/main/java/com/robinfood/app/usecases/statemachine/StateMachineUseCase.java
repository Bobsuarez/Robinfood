package com.robinfood.app.usecases.statemachine;

import com.robinfood.app.factory.IStateUseCaseFactory;
import com.robinfood.core.dtos.state.AbstractState;
import com.robinfood.core.dtos.OrderStateDTO;

import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class StateMachineUseCase implements  IStateMachineUseCase{

    private final IStateUseCaseFactory factoryStateUseCase;

    @Override
    public Boolean invoke(OrderStateDTO actualState,
                                 OrderStateDTO nextState,
                                 List<StatusEntity> stateSystem,
                                 Long idOrder) {

        log.info("Starting process to validate update order status with status code: {}",
                nextState.getCode());

        List<AbstractState> listStates = factoryStateUseCase.getStates(stateSystem);

        AbstractState actualAbstractState = listStates.stream().
                filter(state -> state.getCode().equals(actualState.getCode()))
                .findFirst().orElseThrow(() ->
                        new GenericOrderBcException("State does not exist in the database"));

        if (!actualAbstractState.nextState(nextState)){
            return false;
        }

        String statecode = nextState.getCode();

        if (nextState.getSubState() != null) {
            statecode = nextState.getSubState().getCode();
        }

        String finalStateCode = statecode;

        AbstractState nextAbstractState = listStates.stream().
                filter(state -> state.getCode().equals(finalStateCode))
                .findFirst().orElseThrow(() ->
                        new GenericOrderBcException("State does not exist in the database"));

        return nextAbstractState.action(idOrder);
    }
}
