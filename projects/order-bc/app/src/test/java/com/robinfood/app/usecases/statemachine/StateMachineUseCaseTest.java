package com.robinfood.app.usecases.statemachine;

import com.robinfood.app.usecases.changepaidstate.IChangePaidStateUseCase;
import com.robinfood.app.factory.IStateUseCaseFactory;
import com.robinfood.app.usecases.sendpaidordertoqueue.ISendPaidOrderToQueueUseCase;
import com.robinfood.core.dtos.state.AbstractState;
import com.robinfood.app.factory.state.ApprovedState;
import com.robinfood.app.factory.state.DefaultState;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StateMachineUseCaseTest {
    @Mock
    private IStateUseCaseFactory factoryStateUseCase;

    @Mock
    private IChangePaidStateUseCase changePaidStateUseCase;

    @InjectMocks
    private StateMachineUseCase stateMachineUseCase;

    private final List<StatusEntity> listStateSystem = new ArrayList<>();

    private final List<AbstractState> listAbstractState = new ArrayList<>();

    private final StatusEntity statusEntity = new StatusEntity(
            LocalDateTime.now(),
            "st-001",
            1L,
            "Pedido",
            BigDecimal.valueOf(1),
            LocalDateTime.now(),
            1L
    );
    private final DefaultState defaultIState = new DefaultState(
            BigDecimal.valueOf(1),"PRUEBA"
    );
    private final DefaultState defaultIState2 = new DefaultState(
            BigDecimal.valueOf(2),"PRUEBA2"
    );

    @Mock
    private final ApprovedState defaultIState3 = new ApprovedState(
        BigDecimal.valueOf(2),
        "PRUEBA2",
        changePaidStateUseCase
    );

    private final OrderStateDTO orderStateDTOActual = new OrderStateDTO();
    private final OrderStateDTO orderStateDTOnext = new OrderStateDTO();

    @Test
    void statemachine_success_test() {

        orderStateDTOActual.setName("Prueba");
        orderStateDTOActual.setCode("PRUEBA");
        orderStateDTOActual.setId(1L);
        orderStateDTOActual.setOrder( BigDecimal.valueOf(1));
        orderStateDTOnext.setName("Prueba2");
        orderStateDTOnext.setCode("PRUEBA2");
        orderStateDTOnext.setId(2L);
        orderStateDTOnext.setOrder(BigDecimal.valueOf(2));
        listStateSystem.add(statusEntity);
        listAbstractState.add(defaultIState);
        listAbstractState.add(defaultIState2);

        when(factoryStateUseCase.getStates(listStateSystem)).thenReturn(listAbstractState);

        assertEquals(true, stateMachineUseCase.invoke(
                orderStateDTOActual,orderStateDTOnext,listStateSystem,1L) );
    }

    @Test
    void statemachine_invalidCode_test() {

        orderStateDTOActual.setName("Prueba");
        orderStateDTOActual.setCode("PRUEBA");
        orderStateDTOActual.setId(4L);
        orderStateDTOActual.setOrder(BigDecimal.valueOf(4));
        orderStateDTOnext.setName("Prueba2");
        orderStateDTOnext.setCode("PRUEBA2");
        orderStateDTOnext.setId(2L);
        orderStateDTOnext.setOrder(  BigDecimal.valueOf(2));
        listStateSystem.add(statusEntity);
        defaultIState.setOrder(BigDecimal.valueOf(4));
        listAbstractState.add(defaultIState);
        listAbstractState.add(defaultIState2);

        when(factoryStateUseCase.getStates(listStateSystem)).thenReturn(listAbstractState);

        assertEquals(false, stateMachineUseCase.invoke(
                orderStateDTOActual,orderStateDTOnext,listStateSystem,1L) );

    }

    @Test
    void statemachine_invalidAction_test() {

        orderStateDTOActual.setName("Prueba");
        orderStateDTOActual.setCode("PRUEBA");
        orderStateDTOActual.setId(4L);
        orderStateDTOActual.setOrder(BigDecimal.valueOf(4));
        orderStateDTOnext.setName("Prueba2");
        orderStateDTOnext.setCode("PRUEBA2");
        orderStateDTOnext.setId(2L);
        orderStateDTOnext.setOrder(BigDecimal.valueOf(2));
        listStateSystem.add(statusEntity);
        listAbstractState.add(defaultIState);
        listAbstractState.add(defaultIState3);

        when(factoryStateUseCase.getStates(listStateSystem)).thenReturn(listAbstractState);
        when(defaultIState3.getCode()).thenReturn("PRUEBA2");
        when(defaultIState3.action(1L)).thenReturn(false);

        assertEquals(false, stateMachineUseCase.invoke(
                orderStateDTOActual,orderStateDTOnext,listStateSystem,1L) );

    }

    @Test
    void statemachine_Exception_test() {

        orderStateDTOActual.setName("Prueba");
        orderStateDTOActual.setCode("PRUEBA");
        orderStateDTOActual.setId(4L);
        orderStateDTOnext.setName("Prueba2");
        orderStateDTOnext.setCode("PRUEBA2");
        orderStateDTOnext.setId(2L);
        defaultIState.setCode("aAAAA");
        listStateSystem.add(statusEntity);
        listAbstractState.add(defaultIState);
        listAbstractState.add(defaultIState2);

        when(factoryStateUseCase.getStates(listStateSystem)).thenReturn(listAbstractState);

        assertThrows( GenericOrderBcException.class, () -> stateMachineUseCase.invoke(
                orderStateDTOActual,orderStateDTOnext,listStateSystem,1L));

    }

    @Test
    void statemachine_ExceptionSubState_test() {

        orderStateDTOActual.setName("Prueba");
        orderStateDTOActual.setCode("PRUEBA");
        orderStateDTOActual.setId(4L);
        orderStateDTOActual.setOrder(BigDecimal.valueOf(4));
        orderStateDTOnext.setName("Prueba2");
        orderStateDTOnext.setCode("PRUEBA2");
        orderStateDTOnext.setId(2L);
        orderStateDTOnext.setOrder(BigDecimal.valueOf(2));
        defaultIState2.setCode("aAAAA");
        listStateSystem.add(statusEntity);
        listAbstractState.add(defaultIState);
        listAbstractState.add(defaultIState2);

        when(factoryStateUseCase.getStates(listStateSystem)).thenReturn(listAbstractState);

        assertThrows(GenericOrderBcException.class, () -> stateMachineUseCase.invoke(
                orderStateDTOActual,orderStateDTOnext,listStateSystem,1L));

    }

    @Test
    void statemachine_success_subState_test()  {

        orderStateDTOActual.setName("Prueba");
        orderStateDTOActual.setCode("PRUEBA");
        orderStateDTOActual.setId(1L);
        orderStateDTOActual.setOrder(BigDecimal.valueOf(1));
        orderStateDTOnext.setName("Prueba2");
        orderStateDTOnext.setCode("PRUEBA2");
        orderStateDTOnext.setId(2L);
        orderStateDTOnext.setOrder(  BigDecimal.valueOf(2));
        orderStateDTOnext.setSubState(orderStateDTOActual);
        listStateSystem.add(statusEntity);
        listAbstractState.add(defaultIState);
        listAbstractState.add(defaultIState2);

        when(factoryStateUseCase.getStates(listStateSystem)).thenReturn(listAbstractState);

        assertEquals(true, stateMachineUseCase.invoke(
                orderStateDTOActual,orderStateDTOnext,listStateSystem,1L) );

    }

}
