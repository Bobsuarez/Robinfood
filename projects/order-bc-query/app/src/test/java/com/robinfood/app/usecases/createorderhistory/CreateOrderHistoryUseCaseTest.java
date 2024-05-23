package com.robinfood.app.usecases.createorderhistory;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderHistoryDTODataMock;
import com.robinfood.app.mappers.input.OrderHistoryMappers;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.order.OrderHistoryDTO;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CreateOrderHistoryUseCaseTest {

    @Mock
    private IOrderHistoryRepository orderHistoryRepository;

    @InjectMocks
    private CreateOrderHistoryUseCase createOrderHistoryUseCase;

    private final List<OrderHistoryDTO> orderHistoryDTOList = new OrderHistoryDTODataMock()
            .getDefaultDataList();

    private List<OrderHistoryEntity> orderHistoryEntities = CollectionsKt.map(
            orderHistoryDTOList,
            OrderHistoryMappers::toOrderHistoryEntity
    );

    private final OrderDTO orderDTO = new OrderDTODataMock().getDataDefault(4L);

    @Test
    void test_Create_Order_History(){
        Mockito.when(orderHistoryRepository.saveAll(orderHistoryEntities))
                .thenReturn(orderHistoryEntities);

        Boolean result = createOrderHistoryUseCase
                .invoke(orderDTO.getNotes(), 1L, 100.0, false, 10000.0, 1L)
                .join();

        Mockito.verify(orderHistoryRepository)
                .saveAll(orderHistoryEntities);

        assertTrue(result);
    }

    @Test
    void test_Create_Order_History_total(){

            Mockito.when(orderHistoryRepository.saveAll(orderHistoryEntities))
                    .thenReturn(orderHistoryEntities);


            Boolean result = createOrderHistoryUseCase
                    .invoke(
                            orderDTO.getNotes(),
                            1L,
                            10000.0,
                            true,
                            10000.0,
                            1L
                    )
                    .join();

            Mockito.verify(orderHistoryRepository)
                    .saveAll(orderHistoryEntities);

            assertTrue(result);
    }
}
