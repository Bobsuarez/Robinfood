package com.robinfood.app.usecases.createorderhistory;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderHistoryDTODataMock;
import com.robinfood.app.datamocks.dto.input.RequestOrderTransactionDTOMock;
import com.robinfood.app.mappers.input.OrderHistoryMappers;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.order.OrderHistoryDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class CreateOrderHistoryUseCaseTest {

    @Mock
    private IOrderHistoryRepository orderHistoryRepository;

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private CreateOrderHistoryUseCase createOrderHistoryUseCase;

    private final List<OrderHistoryDTO> orderHistoryDTOList = new OrderHistoryDTODataMock()
            .getDefaultDataList();

    private List<OrderHistoryEntity> orderHistoryEntities = CollectionsKt.map(
            orderHistoryDTOList,
            OrderHistoryMappers::toOrderHistoryEntity
    );

    private final OrderDTO orderDTO = new OrderDTODataMock().getDataDefault(4L);

    private final OrderEntity orderEntity = new OrderEntity();


    private final RequestOrderTransactionDTO transactionDTO = RequestOrderTransactionDTOMock.
            inputOrderTransactionValidatedDTO();

    @Test
    void test_Create_Order_History(){
        Mockito.when(orderHistoryRepository.saveAll(orderHistoryEntities))
                .thenReturn(orderHistoryEntities);
        orderEntity.setId(1L);
        Mockito.when(ordersRepository.findAllByTransactionIdOrderByCreatedAtAsc(1L))
                .thenReturn(List.of(orderEntity));

        Mockito.when(orderHistoryRepository.findByOrderIdAndOrderStatusId(1L,1L))
                .thenReturn(orderHistoryEntities.get(0));
        Boolean result = createOrderHistoryUseCase
                .invoke(orderDTO.getNotes(), 1L, 100.0, false,transactionDTO,
                        10000.0, 1L)
                .join();

        Mockito.verify(orderHistoryRepository)
                .saveAll(orderHistoryEntities);

        assertTrue(result);
    }

    @Test
    void test_Create_Order_History_transactionNUll(){
        Mockito.when(orderHistoryRepository.saveAll(orderHistoryEntities))
                .thenReturn(orderHistoryEntities);
        transactionDTO.setId(null);
        Boolean result = createOrderHistoryUseCase
                .invoke(orderDTO.getNotes(), 1L, 100.0, false,
                        transactionDTO,10000.0, 1L)
                .join();

        Mockito.verify(orderHistoryRepository)
                .saveAll(orderHistoryEntities);

        assertTrue(result);
    }

    @Test
    void test_Create_Order_History_total(){

            Mockito.when(orderHistoryRepository.saveAll(orderHistoryEntities))
                    .thenReturn(orderHistoryEntities);

        Mockito.when(orderHistoryRepository.findByOrderIdAndOrderStatusId(1L,1L))
                .thenReturn(orderHistoryEntities.get(0));

            orderEntity.setId(1L);
            Mockito.when(ordersRepository.findAllByTransactionIdOrderByCreatedAtAsc(1L))
                    .thenReturn(List.of(orderEntity));

            Boolean result = createOrderHistoryUseCase
                    .invoke(
                            orderDTO.getNotes(),
                            1L,
                            10000.0,
                            true,
                            transactionDTO,
                            10000.0,
                            1L
                    )
                    .join();

            Mockito.verify(orderHistoryRepository)
                    .saveAll(orderHistoryEntities);

            assertTrue(result);
    }

    @Test
    void test_Create_Order_History_total_Exception(){

        orderEntity.setId(1L);
        Mockito.when(ordersRepository.findAllByTransactionIdOrderByCreatedAtAsc(1L))
                .thenReturn(List.of());

        assertThrows(GenericOrderBcException.class, () -> {
            createOrderHistoryUseCase
                    .invoke(
                            orderDTO.getNotes(),
                            1L,
                            10000.0,
                            true,
                            transactionDTO,
                            10000.0,
                            1L
                    )
                    .join();
        });
    }
}
