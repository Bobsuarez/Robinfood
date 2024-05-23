package com.robinfood.changestatusbc.factories.state;

import com.robinfood.changestatusbc.dtos.AbstractState;
import com.robinfood.changestatusbc.entities.StatusEntity;
import com.robinfood.changestatusbc.usecases.changepaidstate.IChangePaidStateUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.ORDER_APPROVED_PAYMENT;

@Component
@Slf4j
public class StateUseCaseFactory implements IStateUseCaseFactory {

    private final IChangePaidStateUseCase changePaidStateUseCase;

    public StateUseCaseFactory(IChangePaidStateUseCase changePaidStateUseCase) {
        this.changePaidStateUseCase = changePaidStateUseCase;
    }

    @Override
    public List<AbstractState> getStates(List<StatusEntity> listStatusEntities) {

        return listStatusEntities.stream().map(this::createState).collect(Collectors.toList());
    }

    private AbstractState createState(StatusEntity state) {

        if (state.getCode().equals(ORDER_APPROVED_PAYMENT)) {
            return  new ApprovedState(
                state.getOrder(),
                state.getCode(),
                changePaidStateUseCase
            );
        }

        return new DefaultState(state.getOrder(),state.getCode());
    }
 }
