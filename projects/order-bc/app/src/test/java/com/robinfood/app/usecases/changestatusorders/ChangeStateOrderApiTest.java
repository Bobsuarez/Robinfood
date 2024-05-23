package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.app.facades.IGetOrderStateFacade;
import com.robinfood.app.usecases.changeorderfinalproductportions.IChangeOrderFinalProductPortionsUseCase;
import com.robinfood.app.usecases.changestatewithsubstate.IChangeStateWithSubStateUseCase;
import com.robinfood.app.usecases.getallstate.IGetAllStateUseCase;
import com.robinfood.app.usecases.getstate.IGetStateOrderWithCodeUseCase;
import com.robinfood.app.usecases.statemachine.IStateMachineUseCase;
import com.robinfood.app.usecases.writechangestatusqueue.IWriteChangeStatusQueueUseCase;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.entities.StatusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeStateOrderApiTest {

    @InjectMocks
    ChangeOrderStateUseCase changeOrderStateUseCase;

    @Mock
    private IGetStateOrderWithCodeUseCase getStateUseCase;

    @Mock
    private IGetAllStateUseCase getAllStateUseCase;

    @Mock
    private IStateMachineUseCase stateMachineUseCase;

    @Mock
    private IGetOrderStateFacade getOrderStateFacade;

    @Mock
    private IChangeStateWithSubStateUseCase changeStateWithSubStateUseCase;

    @Mock
    private IWriteChangeStatusQueueUseCase writeChangeStatusQueueUseCase;

    @Mock
    private IChangeOrderFinalProductPortionsUseCase changeOrderFinalProductPortionsUseCase;

    private final List<StatusEntity> listStatus = new ArrayList<>();

    private final StatusEntity statusEntity = new StatusEntity(
            LocalDateTime.now(),
            "st-001",
            1L,
            "Pedido",
            BigDecimal.valueOf(1),
            LocalDateTime.now(),
            1L
    );

    private final OrderStateDTO orderStateDTO = new OrderStateDTO();
    private final OrderStateDTO subStateOrder = new OrderStateDTO();
    private final ChangeOrderStatusDTO changeOrderStatusDTO = new ChangeOrderStatusDTO();

    @Test
    void change_Order_Status_Consumer_Success_Test() {

        changeOrderStatusDTO.setStatusId(1L);
        changeOrderStatusDTO.setOrderId(1L);
        changeOrderStatusDTO.setNotes("Prueba");
        changeOrderStatusDTO.setStatusCode("PRUEBA");
        changeOrderStatusDTO.setUserId(1L);

        listStatus.add(statusEntity);
        orderStateDTO.setOrderId(1L);
        subStateOrder.setOrderId(1L);
        when(getOrderStateFacade.invoke(changeOrderStatusDTO)).thenReturn(orderStateDTO);
        when(getStateUseCase.invoke("PRUEBA")).thenReturn(subStateOrder);
        when(getAllStateUseCase.invoke()).thenReturn(listStatus);
        when(stateMachineUseCase.invoke(orderStateDTO,subStateOrder,listStatus,1L)).thenReturn(true);
        when(changeStateWithSubStateUseCase.invoke(1L,subStateOrder)).thenReturn(true);

        changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        verify(getOrderStateFacade,times(1)).invoke(changeOrderStatusDTO);
        verify(writeChangeStatusQueueUseCase, times(1)).invoke(changeOrderStatusDTO);
        verify(changeOrderFinalProductPortionsUseCase, times(1))
                .invoke(changeOrderStatusDTO.getOrderId(), changeOrderStatusDTO.getStatusCode());
    }

    @Test
    void change_Order_Status_Consumer_Fail_Test() {

        changeOrderStatusDTO.setStatusId(1L);
        changeOrderStatusDTO.setOrderId(1L);
        changeOrderStatusDTO.setNotes("Prueba");
        changeOrderStatusDTO.setStatusCode("PRUEBA");
        changeOrderStatusDTO.setUserId(1L);

        listStatus.add(statusEntity);
        orderStateDTO.setOrderId(1L);
        subStateOrder.setOrderId(1L);
        when(getOrderStateFacade.invoke(changeOrderStatusDTO)).thenReturn(orderStateDTO);
        when(getStateUseCase.invoke("PRUEBA")).thenReturn(subStateOrder);
        when(getAllStateUseCase.invoke()).thenReturn(listStatus);
        when(stateMachineUseCase.invoke(orderStateDTO,subStateOrder,listStatus,1L)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        });
    }

    @Test
    void change_Order_Status_Consumer_Is_False_Test() throws Exception {

        changeOrderStatusDTO.setStatusId(1L);
        changeOrderStatusDTO.setOrderId(1L);
        changeOrderStatusDTO.setNotes("Prueba");
        changeOrderStatusDTO.setStatusCode("PRUEBA");
        changeOrderStatusDTO.setUserId(1L);

        listStatus.add(statusEntity);

        orderStateDTO.setOrderId(1L);

        subStateOrder.setOrderId(1L);

        when(getOrderStateFacade.invoke(changeOrderStatusDTO)).thenReturn(orderStateDTO);
        when(getStateUseCase.invoke("PRUEBA")).thenReturn(subStateOrder);
        when(getAllStateUseCase.invoke()).thenReturn(listStatus);
        when(stateMachineUseCase.invoke(orderStateDTO,subStateOrder,listStatus,1L)).thenReturn(true);
        when(changeStateWithSubStateUseCase.invoke(1L,subStateOrder)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        });
    }

    @Test
    void when_Origin_Id_Self_Management_Not_Null_Success_Test() {

        final Long ORIGIN_ID_OF_SELF_MANAGEMENT = 4L;

        changeOrderStatusDTO.setStatusId(1L);
        changeOrderStatusDTO.setOrderId(1L);
        changeOrderStatusDTO.setNotes("Prueba");
        changeOrderStatusDTO.setStatusCode("PRUEBA");
        changeOrderStatusDTO.setUserId(1L);

        listStatus.add(statusEntity);
        orderStateDTO.setOrderId(1L);
        orderStateDTO.setOriginId(ORIGIN_ID_OF_SELF_MANAGEMENT);
        subStateOrder.setOrderId(1L);
        when(getOrderStateFacade.invoke(changeOrderStatusDTO)).thenReturn(orderStateDTO);
        when(getStateUseCase.invoke("PRUEBA")).thenReturn(subStateOrder);
        when(getAllStateUseCase.invoke()).thenReturn(listStatus);
        when(stateMachineUseCase.invoke(orderStateDTO,subStateOrder,listStatus,1L)).thenReturn(true);
        when(changeStateWithSubStateUseCase.invoke(1L,subStateOrder)).thenReturn(true);

        changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        verify(getOrderStateFacade,times(1)).invoke(changeOrderStatusDTO);
        verify(writeChangeStatusQueueUseCase, times(1)).invoke(changeOrderStatusDTO);
        verify(changeOrderFinalProductPortionsUseCase, times(1))
                .invoke(changeOrderStatusDTO.getOrderId(), changeOrderStatusDTO.getStatusCode());
    }

    @Test
    void when_Order_Is_Paid_Success_Test() {

        final Long ORIGIN_ID_OF_SELF_MANAGEMENT = 4L;

        changeOrderStatusDTO.setStatusId(1L);
        changeOrderStatusDTO.setOrderId(1L);
        changeOrderStatusDTO.setNotes("Prueba");
        changeOrderStatusDTO.setStatusCode("PRUEBA");
        changeOrderStatusDTO.setUserId(1L);

        listStatus.add(statusEntity);
        orderStateDTO.setOrderId(1L);
        orderStateDTO.setOriginId(ORIGIN_ID_OF_SELF_MANAGEMENT);
        orderStateDTO.setIsPaid(true);
        subStateOrder.setOrderId(1L);

        when(getOrderStateFacade.invoke(changeOrderStatusDTO)).thenReturn(orderStateDTO);
        when(getStateUseCase.invoke("PRUEBA")).thenReturn(subStateOrder);
        when(getAllStateUseCase.invoke()).thenReturn(listStatus);
        when(stateMachineUseCase.invoke(orderStateDTO,subStateOrder,listStatus,1L)).thenReturn(true);
        when(changeStateWithSubStateUseCase.invoke(1L,subStateOrder)).thenReturn(true);

        changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        verify(getOrderStateFacade,times(1)).invoke(changeOrderStatusDTO);
        verify(writeChangeStatusQueueUseCase, times(2)).invoke(changeOrderStatusDTO);
        verify(changeOrderFinalProductPortionsUseCase, times(1))
                .invoke(changeOrderStatusDTO.getOrderId(), changeOrderStatusDTO.getStatusCode());
    }

    @Test
    void when_Origin_Id_Not_Self_Management_Not_Null_Success_Test() {

        final Long ORIGIN_ID_OF_ROBINFOOD_APP = 2L;

        changeOrderStatusDTO.setStatusId(1L);
        changeOrderStatusDTO.setOrderId(1L);
        changeOrderStatusDTO.setNotes("Prueba");
        changeOrderStatusDTO.setStatusCode("PRUEBA");
        changeOrderStatusDTO.setUserId(1L);

        listStatus.add(statusEntity);
        orderStateDTO.setOrderId(1L);
        orderStateDTO.setOriginId(ORIGIN_ID_OF_ROBINFOOD_APP);
        orderStateDTO.setIsPaid(false);
        subStateOrder.setOrderId(1L);

        when(getOrderStateFacade.invoke(changeOrderStatusDTO)).thenReturn(orderStateDTO);
        when(getStateUseCase.invoke("PRUEBA")).thenReturn(subStateOrder);
        when(getAllStateUseCase.invoke()).thenReturn(listStatus);
        when(stateMachineUseCase.invoke(orderStateDTO,subStateOrder,listStatus,1L)).thenReturn(true);
        when(changeStateWithSubStateUseCase.invoke(1L,subStateOrder)).thenReturn(true);

        changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        verify(getOrderStateFacade,times(1)).invoke(changeOrderStatusDTO);
        verify(writeChangeStatusQueueUseCase, times(1)).invoke(changeOrderStatusDTO);
        verify(changeOrderFinalProductPortionsUseCase, times(1))
                .invoke(changeOrderStatusDTO.getOrderId(), changeOrderStatusDTO.getStatusCode());
    }
}
