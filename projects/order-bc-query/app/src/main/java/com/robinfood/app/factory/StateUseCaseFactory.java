package com.robinfood.app.factory;

import com.robinfood.app.usecases.changepaidstate.IChangePaidStateUseCase;
import com.robinfood.app.factory.state.ApprovedState;
import com.robinfood.app.factory.state.DefaultState;
import com.robinfood.core.dtos.state.AbstractState;
import com.robinfood.core.entities.StatusEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.ORDER_APPROVED_PAYMENT;

@AllArgsConstructor
@Component
@Slf4j
public class StateUseCaseFactory implements IStateUseCaseFactory {

    private final IChangePaidStateUseCase changePaidStateUseCase;

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
