package com.robinfood.app.usecases.factorystateusecase;

import com.robinfood.app.usecases.changepaidstate.IChangePaidStateUseCase;
import com.robinfood.app.factory.StateUseCaseFactory;
import com.robinfood.core.dtos.state.AbstractState;
import com.robinfood.app.factory.state.DefaultState;
import com.robinfood.core.entities.StatusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FactoryStateUseCaseTest {

    @Mock
    private IChangePaidStateUseCase changePaidStateUseCase;

    @InjectMocks
    private StateUseCaseFactory factoryStateUseCase;

    private final List<StatusEntity> listStateSystem = new ArrayList<>();

    private final List<AbstractState> listAbstractState = new ArrayList<>();

    private final DefaultState defaultIState = new DefaultState(
            BigDecimal.valueOf(1),"PRUEBA"
    );
    private final DefaultState defaultIState2 = new DefaultState(
            BigDecimal.valueOf(2),"ORDER_APPROVED_PAYMENT"
    );

    private final StatusEntity statusEntity = new StatusEntity(
            LocalDateTime.now(),
            "PRUEBA",
            1L,
            "Pedido",
            BigDecimal.valueOf(1),
            LocalDateTime.now(),

            1L
    );
    private final StatusEntity statusEntity2 = new StatusEntity(
            LocalDateTime.now(),
            "ORDER_APPROVED_PAYMENT",
            2L,
            "Pedido2",
            BigDecimal.valueOf(2),
            LocalDateTime.now(),
            1L
    );

    @Test
    void factoryState_success_test () {

        listStateSystem.add(statusEntity);
        listAbstractState.add(defaultIState);


        listStateSystem.add(statusEntity2);
        listAbstractState.add(defaultIState2);

        assertEquals(listAbstractState, factoryStateUseCase.getStates(listStateSystem));

    }
}
