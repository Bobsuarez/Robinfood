package com.robinfood.changestatusbc.usecases.getstate;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.entities.StatusEntity;
import com.robinfood.changestatusbc.exceptions.GenericChangeStatusBcException;
import com.robinfood.changestatusbc.mappers.OrderStateMappers;
import com.robinfood.changestatusbc.repositories.status.IStatusRepository;
import datamock.OrderStateDTODataMock;
import datamock.StatusEntityDataMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStateOrderWithCodeUseCaseTest {

    @Mock
    private IStatusRepository statusRepository;

    @Mock
    private OrderStateMappers orderStateMappers;

    @InjectMocks
    private GetStateOrderWithCodeUseCase getStateOrderWithCodeUseCase;

    private final StatusEntity statusEntity = new StatusEntityDataMock().getDefaultData();

    private final StatusEntity parent = new StatusEntityDataMock().getDefaultData();

    private final StatusEntity statusEntityOrderTen = new StatusEntityDataMock().getDefaultDataOrder();

    private final OrderStateDTO orderStateDTO = new OrderStateDTODataMock().getDefaultData();

    private final String DEFAULT_CODE = "CODE";

    @Test
    void test_Get_State_Order_With_Code_Use_case_Invoke(){

        when(statusRepository.findByCode(anyString())).thenReturn(Optional.ofNullable(statusEntity));

        when(orderStateMappers.buildOrderState(statusEntity)).thenReturn(orderStateDTO);

        assertAll(()-> getStateOrderWithCodeUseCase.invoke(DEFAULT_CODE));
    }

    @Test
    void test_Get_State_Order_With_Code_Use_case_Invoke_Invalid_Code(){

        when(statusRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(GenericChangeStatusBcException.class, () -> getStateOrderWithCodeUseCase.invoke(DEFAULT_CODE));

    }

    @Test
    void test_Get_State_Order_With_Code_Use_case_Invoke_Invalid_Code_And_Invalid_Parent(){

        when(statusRepository.findByCode(anyString())).thenReturn(Optional.of(statusEntityOrderTen));

        when(statusRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GenericChangeStatusBcException.class, () -> getStateOrderWithCodeUseCase.invoke(DEFAULT_CODE));

    }

}