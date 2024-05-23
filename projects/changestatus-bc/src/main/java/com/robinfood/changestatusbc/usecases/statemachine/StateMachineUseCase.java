package com.robinfood.changestatusbc.usecases.statemachine;

import com.robinfood.changestatusbc.dtos.AbstractState;
import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.entities.StatusEntity;
import com.robinfood.changestatusbc.exceptions.GenericChangeStatusBcException;
import com.robinfood.changestatusbc.factories.state.IStateUseCaseFactory;
import com.robinfood.changestatusbc.utilities.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.robinfood.changestatusbc.enums.AppTraceEnum.STARTING_PROCESS_VALIDATE;
import static com.robinfood.changestatusbc.enums.AppTraceEnum.STATES_LIST_FACTORY;


@Component
@Slf4j
public class StateMachineUseCase implements IStateMachineUseCase {

    private final IStateUseCaseFactory factoryStateUseCase;

    public StateMachineUseCase(IStateUseCaseFactory factoryStateUseCase) {
        this.factoryStateUseCase = factoryStateUseCase;
    }

    @Override
    public Boolean invoke(
            OrderStateDTO actualState,
            OrderStateDTO nextState,
            List<StatusEntity> stateSystem,
            Long idOrder
    ) {

        log.info(STARTING_PROCESS_VALIDATE.getMessageWithCode(), nextState.getCode());

        List<AbstractState> listStates = factoryStateUseCase.getStates(stateSystem);

        log.info(STATES_LIST_FACTORY.getMessageWithCode(), ObjectMapperSingleton.objectToJson(listStates));

        AbstractState actualAbstractState = listStates.stream()
                .filter(state -> state.getCode().equals(actualState.getCode()))
                .findFirst()
                .orElseThrow(() -> new GenericChangeStatusBcException("State does not exist in the database"));

        if (Boolean.FALSE.equals(actualAbstractState.nextState(nextState))) {
            return false;
        }

        String statecode = nextState.getCode();

        if (nextState.getSubState() != null) {
            statecode = nextState.getSubState().getCode();
        }

        String finalStateCode = statecode;

        AbstractState nextAbstractState = listStates.stream()
                .filter(state -> state.getCode().equals(finalStateCode))
                .findFirst()
                .orElseThrow(() -> new GenericChangeStatusBcException("State does not exist in the database"));

        return nextAbstractState.action(idOrder);
    }
}
