package com.robinfood.changestatusbc.usecases.changestatewithsubstate;

import com.robinfood.changestatusbc.dtos.OrderBrandHistoryDTO;
import com.robinfood.changestatusbc.dtos.OrderHistoryDTO;
import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.entities.OrderBrandHistoryEntity;
import com.robinfood.changestatusbc.entities.OrderEntity;
import com.robinfood.changestatusbc.entities.OrderHistoryEntity;
import com.robinfood.changestatusbc.mappers.OrderBrandHistoryMappers;
import com.robinfood.changestatusbc.mappers.OrderHistoryMappers;
import com.robinfood.changestatusbc.repositories.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.changestatusbc.repositories.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.changestatusbc.repositories.orderhistory.IOrderHistoryRepository;
import com.robinfood.changestatusbc.repositories.orders.IOrdersRepository;
import datamock.OrderBrandHistoryDTODataMock;
import datamock.OrderEntityMock;
import datamock.OrderHistoryDTODataMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeStateWithSubStateUseCaseTest {
    @Mock
    private IOrderHistoryRepository mockOrderHistoryRepository;

    @Mock
    private IOrdersRepository mockOrdersRepository;

    @Mock
    private IOrderBrandHistoryRepository mockOrderBrandHistoryRepository;

    @Mock
    private IOrderFinalProductRepository mockOrderFinalProductRepository;

    @InjectMocks
    private ChangeStateWithSubStateUseCase changeStateWithSubStateUseCase;

    private final OrderEntity orderEntityMock = new OrderEntityMock().getDataDefault();


    private final List<OrderBrandHistoryDTO> orderBrandHistoryDTOList = new OrderBrandHistoryDTODataMock()
            .getDefaultDataList();

    private final List<OrderHistoryDTO> orderHistoryDTOList = new OrderHistoryDTODataMock()
            .getDefaultDataList();

    private final List<OrderHistoryDTO> orderHistoryDTOListTwoValues = new OrderHistoryDTODataMock()
            .getDefaultDataListTwoValues();

    private final List<OrderBrandHistoryEntity> orderBrandHistoryEntities = orderBrandHistoryDTOList.stream()
            .map(OrderBrandHistoryMappers::toOrderBrandHistoryEntity)
            .collect(Collectors.toList());

    private final List<OrderHistoryEntity> orderHistoryEntities = orderHistoryDTOList.stream()
            .map(OrderHistoryMappers::toOrderHistoryEntity)
            .collect(Collectors.toList());

    List<OrderHistoryEntity> orderHistoryEntitiesTwoValues = orderHistoryDTOListTwoValues.stream()
            .map(OrderHistoryMappers::toOrderHistoryEntity)
            .collect(Collectors.toList());

    @Test
    void test_Change_Status_Succes_Parent () {

        OrderStateDTO orderStateDTO = new OrderStateDTO();
        orderStateDTO.setId(1L);
        orderStateDTO.setIdUser(1L);
        orderStateDTO.setNotes("Anything");
        orderEntityMock.setStatusId(2L);
        orderEntityMock.setId(1L);
        orderEntityMock.setUserId(1L);

        when(mockOrderFinalProductRepository.findAllDistinctBrandIdByOrderId(1L))
                .thenReturn(new ArrayList<>(Collections.singletonList(
                        1L
                )));

        when(mockOrderBrandHistoryRepository.saveAll(orderBrandHistoryEntities))
                .thenReturn(orderBrandHistoryEntities);

        when(mockOrdersRepository.findById(1L)).thenReturn(Optional.of(orderEntityMock));

        when(mockOrdersRepository.save(orderEntityMock)).thenReturn(orderEntityMock);
        when(mockOrderHistoryRepository.saveAll(orderHistoryEntities))
                .thenReturn(orderHistoryEntities);

        assertTrue(changeStateWithSubStateUseCase.invoke(1L,orderStateDTO));

    }

    @Test
    void test_Change_Status_Succes_Equals_Not_Entity_Parent () {

        OrderStateDTO orderStateDTO = new OrderStateDTO();
        OrderStateDTO orderSubStateDTO = new OrderStateDTO();
        orderSubStateDTO.setId(3L);
        orderStateDTO.setId(2L);
        orderStateDTO.setIdUser(1L);
        orderStateDTO.setSubState(orderSubStateDTO);
        orderStateDTO.setNotes("Anything");
        orderEntityMock.setStatusId(1L);
        orderEntityMock.setId(1L);
        orderEntityMock.setUserId(1L);
        when(mockOrdersRepository.findById(1L)).thenReturn(Optional.of(orderEntityMock));

        when(mockOrdersRepository.save(orderEntityMock)).thenReturn(orderEntityMock);
        when(mockOrderHistoryRepository.saveAll(orderHistoryEntitiesTwoValues))
                .thenReturn(orderHistoryEntities);

        assertTrue(changeStateWithSubStateUseCase.invoke(1L,orderStateDTO));

    }

    @Test
    void test_Change_SubStatus_Succes_Parent () {

        OrderStateDTO orderStateDTO = new OrderStateDTO();
        OrderStateDTO subOrderStateDTO = new OrderStateDTO();
        subOrderStateDTO.setId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setIdUser(1L);
        orderStateDTO.setNotes("Anything");
        orderStateDTO.setSubState(subOrderStateDTO);
        orderEntityMock.setStatusId(1L);
        orderEntityMock.setId(1L);
        orderEntityMock.setUserId(1L);
        when(mockOrdersRepository.findById(1L)).thenReturn(Optional.of(orderEntityMock));

        when(mockOrdersRepository.save(orderEntityMock)).thenReturn(orderEntityMock);
        when(mockOrderHistoryRepository.saveAll(orderHistoryEntities))
                .thenReturn(orderHistoryEntities);

        assertTrue(changeStateWithSubStateUseCase.invoke(1L,orderStateDTO));
    }

    @Test
    void test_Change_Status_Exception_Parent () {

        OrderStateDTO orderStateDTO = new OrderStateDTO();
        OrderStateDTO subOrderStateDTO = new OrderStateDTO();
        subOrderStateDTO.setId(1L);
        orderStateDTO.setId(1L);
        orderStateDTO.setIdUser(1L);
        orderStateDTO.setNotes("Anything");
        orderStateDTO.setSubState(subOrderStateDTO);
        orderEntityMock.setStatusId(1L);
        orderEntityMock.setId(1L);
        orderEntityMock.setUserId(1L);
        when(mockOrdersRepository.findById(1L)).thenThrow(IllegalArgumentException.class);


        assertThrows(IllegalArgumentException.class, () -> {
            changeStateWithSubStateUseCase.invoke(1L,orderStateDTO);
        });

    }

}