package com.robinfood.changestatusbc.usecases.changestatus;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.dtos.WriteChangeStatusDTO;
import com.robinfood.changestatusbc.entities.StatusEntity;
import com.robinfood.changestatusbc.exceptions.GenericChangeStatusBcException;
import com.robinfood.changestatusbc.facades.IGetOrderStateFacade;
import com.robinfood.changestatusbc.usecases.changeorderfinalproductportions.IChangeOrderFinalProductPortionsUseCase;
import com.robinfood.changestatusbc.usecases.changestatewithsubstate.IChangeStateWithSubStateUseCase;
import com.robinfood.changestatusbc.usecases.getallstate.IGetAllStateUseCase;
import com.robinfood.changestatusbc.usecases.getstate.IGetStateOrderWithCodeUseCase;
import com.robinfood.changestatusbc.usecases.statemachine.IStateMachineUseCase;
import com.robinfood.changestatusbc.usecases.writechangestatusqueue.IWriteChangeStatusQueueUseCase;
import datamock.ChangeOrderStatusDTODataMock;
import datamock.OrderStateDTODataMock;
import datamock.StatusEntityDataMock;
import datamock.WriteChangeStatusDTODataMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeOrderStateUseCaseTest {

    @Mock
    private IChangeStateWithSubStateUseCase changeStateWithSubStateUseCase;

    @Mock
    private IChangeOrderFinalProductPortionsUseCase changeOrderFinalProductPortionsUseCase;

    @Mock
    private IGetStateOrderWithCodeUseCase getStateOrderWithCodeUseCase;

    @Mock
    private IGetOrderStateFacade getOrderStateFacade;

    @Mock
    private IGetAllStateUseCase getAllStateUseCase;

    @Mock
    private IStateMachineUseCase stateMachineUseCase;

    @Mock
    private IWriteChangeStatusQueueUseCase writeChangeStatusQueueUseCase;

    @InjectMocks
    private ChangeOrderStateUseCase changeOrderStateUseCase;

    private final OrderStateDTO orderStateDTO = new OrderStateDTODataMock().getDefaultData();

    private final OrderStateDTO orderStatePosDTO = new OrderStateDTODataMock().getDefaultDataPOS();

    private final OrderStateDTO orderStatePosNotPaidDTO = new OrderStateDTODataMock().getDefaultDataPOSNotPaid();

    private final List<StatusEntity> statusEntityList = new StatusEntityDataMock().getDefaultDataList();

    private final WriteChangeStatusDTO writeChangeStatusDTO = new WriteChangeStatusDTODataMock().getDefaultData();

    private final ChangeOrderStatusDTO changeOrderStatusDTO = new ChangeOrderStatusDTODataMock().getDefaultData();

    private final boolean DEFAULT_BOOLEAN_TRUE = true;

    private final boolean DEFAULT_BOOLEAN_FALSE = false;

    @Test
    void test_Change_Order_State_Use_Case_Success(){

        when(getOrderStateFacade.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(orderStateDTO);

        when(getStateOrderWithCodeUseCase.invoke(changeOrderStatusDTO.getStatusCode())).thenAnswer(invocation -> orderStateDTO);

        when(getAllStateUseCase.invoke()).thenReturn(statusEntityList);

        when(stateMachineUseCase.invoke(
                orderStateDTO,
                orderStateDTO,
                statusEntityList,
                1L
                )
        ).thenReturn(DEFAULT_BOOLEAN_TRUE);

        when(changeStateWithSubStateUseCase.invoke(
                anyLong(),
                any(OrderStateDTO.class))
        ).thenReturn(DEFAULT_BOOLEAN_TRUE);

        when(writeChangeStatusQueueUseCase.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(writeChangeStatusDTO);

        assertAll(()-> changeOrderStateUseCase.invoke(changeOrderStatusDTO));
    }

    @Test
    void test_Change_Order_State_Use_Case_Success_Data_POS(){

        when(getOrderStateFacade.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(orderStatePosDTO);

        when(getStateOrderWithCodeUseCase.invoke(changeOrderStatusDTO.getStatusCode())).thenAnswer(invocation -> orderStatePosDTO);

        when(getAllStateUseCase.invoke()).thenReturn(statusEntityList);

        when(stateMachineUseCase.invoke(
                orderStatePosDTO,
                orderStatePosDTO,
                        statusEntityList,
                        1L
                )
        ).thenReturn(DEFAULT_BOOLEAN_TRUE);

        when(changeStateWithSubStateUseCase.invoke(
                anyLong(),
                any(OrderStateDTO.class))
        ).thenReturn(DEFAULT_BOOLEAN_TRUE);

        when(writeChangeStatusQueueUseCase.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(writeChangeStatusDTO);

        assertAll(()-> changeOrderStateUseCase.invoke(changeOrderStatusDTO));
    }

    @Test
    void test_Change_Order_State_Use_Case_Success_Data_POS_Not_Paid(){

        when(getOrderStateFacade.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(orderStatePosNotPaidDTO);

        when(getStateOrderWithCodeUseCase.invoke(changeOrderStatusDTO.getStatusCode())).thenAnswer(invocation ->
                orderStatePosNotPaidDTO);

        when(getAllStateUseCase.invoke()).thenReturn(statusEntityList);

        when(stateMachineUseCase.invoke(
                orderStatePosNotPaidDTO,
                orderStatePosNotPaidDTO,
                        statusEntityList,
                        1L
                )
        ).thenReturn(DEFAULT_BOOLEAN_TRUE);

        when(changeStateWithSubStateUseCase.invoke(
                anyLong(),
                any(OrderStateDTO.class))
        ).thenReturn(DEFAULT_BOOLEAN_TRUE);

        when(writeChangeStatusQueueUseCase.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(writeChangeStatusDTO);

        assertAll(()-> changeOrderStateUseCase.invoke(changeOrderStatusDTO));
    }

    @Test
    void test_Change_Order_State_Use_Case_Exception_state_machine(){

        when(getOrderStateFacade.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(orderStateDTO);

        when(getStateOrderWithCodeUseCase.invoke(changeOrderStatusDTO.getStatusCode())).thenAnswer(invocation -> orderStateDTO);

        when(getAllStateUseCase.invoke()).thenReturn(statusEntityList);

        when(stateMachineUseCase.invoke(
                        orderStateDTO,
                        orderStateDTO,
                        statusEntityList,
                        1L
                )
        ).thenReturn(DEFAULT_BOOLEAN_FALSE);

        assertThrows(ResponseStatusException.class, () -> {
            changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        });

    }


    @Test
    void test_Change_Order_State_Use_Case_Exception(){

        when(getOrderStateFacade.invoke(any(ChangeOrderStatusDTO.class))).thenReturn(orderStateDTO);

        when(getStateOrderWithCodeUseCase.invoke(changeOrderStatusDTO.getStatusCode())).thenAnswer(invocation -> orderStateDTO);

        when(getAllStateUseCase.invoke()).thenReturn(statusEntityList);

        when(stateMachineUseCase.invoke(
                        orderStateDTO,
                        orderStateDTO,
                        statusEntityList,
                        1L
                )
        ).thenReturn(DEFAULT_BOOLEAN_TRUE);

        when(changeStateWithSubStateUseCase.invoke(
                anyLong(),
                any(OrderStateDTO.class))
        ).thenReturn(DEFAULT_BOOLEAN_FALSE);

        assertThrows(ResponseStatusException.class, () -> {
            changeOrderStateUseCase.invoke(changeOrderStatusDTO);
        });
    }

}
