package com.robinfood.app.usecases.changestatewithsubstate;

import com.robinfood.app.datamocks.dto.input.OrderBrandHistoryDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderHistoryDTODataMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.mappers.input.OrderBrandHistoryMappers;
import com.robinfood.app.mappers.input.OrderHistoryMappers;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.request.changestatusorders.OrderStatusDTO;
import com.robinfood.core.dtos.request.order.OrderBrandHistoryDTO;
import com.robinfood.core.dtos.request.order.OrderHistoryDTO;
import com.robinfood.core.entities.OrderBrandHistoryEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.repository.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeStateWithSubStateUseCaseTest {

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

    private final List<OrderBrandHistoryEntity> orderBrandHistoryEntities = CollectionsKt.map(
            orderBrandHistoryDTOList,
            OrderBrandHistoryMappers::toOrderBrandHistoryEntity
    );

    private final List<OrderHistoryEntity> orderHistoryEntities = CollectionsKt.map(
            orderHistoryDTOList,
            OrderHistoryMappers::toOrderHistoryEntity
    );

    private final List<OrderHistoryEntity> orderHistoryEntitiesTwoValues = CollectionsKt.map(
            orderHistoryDTOListTwoValues,
            OrderHistoryMappers::toOrderHistoryEntity
    );

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
