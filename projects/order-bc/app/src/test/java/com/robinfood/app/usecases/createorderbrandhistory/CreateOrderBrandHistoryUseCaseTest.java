package com.robinfood.app.usecases.createorderbrandhistory;

import com.robinfood.app.datamocks.dto.input.OrderBrandHistoryDTODataMock;
import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.app.datamocks.dto.input.RequestOrderTransactionDTOMock;
import com.robinfood.app.mappers.input.OrderBrandHistoryMappers;
import com.robinfood.app.usecases.getbrandidsbyorder.IGetBrandIdsByOrder;
import com.robinfood.core.dtos.request.order.OrderBrandHistoryDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderBrandHistoryEntity;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderbrandhistory.IOrderBrandHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CreateOrderBrandHistoryUseCaseTest {

    @Mock
    private IOrderBrandHistoryRepository orderBrandHistoryRepository;

    @Mock
    private IGetBrandIdsByOrder getBrandIdsByOrder;

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private CreateOrderBrandHistoryUseCase createOrderBrandHistoryUseCase;

    private final List<OrderBrandHistoryDTO> orderBrandHistoryDTOList = new OrderBrandHistoryDTODataMock()
            .getDefaultDataList();

    private final List<OrderBrandHistoryEntity> orderBrandHistoryEntities = CollectionsKt.map(
            orderBrandHistoryDTOList,
            OrderBrandHistoryMappers::toOrderBrandHistoryEntity
    );
    private final OrderEntity orderEntity = new OrderEntity();
    private final RequestOrderTransactionDTO transactionDTO = RequestOrderTransactionDTOMock.inputOrderTransactionValidatedDTO();
    private final OrderDTO orderDTO = new OrderDTODataMock().getDataDefault(4L);

    private final List<Long> brandIds = new ArrayList<>(Arrays.asList(1L));

    @Test
    void test_Create_Order_Brand_History(){
        Mockito.when(orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities))
                .thenReturn(orderBrandHistoryEntities);
        orderEntity.setId(1L);
        Mockito.when(ordersRepository.findAllByTransactionIdOrderByCreatedAtAsc(1L))
                .thenReturn(List.of(orderEntity));
        Mockito.when(orderBrandHistoryRepository.findAllByOrderIdAndOrderStatusId(1L, 1L))
                .thenReturn(orderBrandHistoryEntities);
        Mockito.when(getBrandIdsByOrder.invoke(orderDTO.getFinalProducts()))
                .thenReturn(brandIds);

        Boolean result = createOrderBrandHistoryUseCase
                .invoke(
                        orderDTO.getFinalProducts(),
                        1L,
                        1000.0,
                        false,
                        transactionDTO,
                        10000.0,
                        1L
                )
                .join();

        Mockito.verify(orderBrandHistoryRepository,times(2))
                .saveAll(orderBrandHistoryEntities);

        assertTrue(result);
    }

    @Test
    void test_Create_Order_Brand_History_Transaction_Null(){
        Mockito.when(orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities))
                .thenReturn(orderBrandHistoryEntities);

        Mockito.when(getBrandIdsByOrder.invoke(orderDTO.getFinalProducts()))
                .thenReturn(brandIds);
        transactionDTO.setId(null);
        Boolean result = createOrderBrandHistoryUseCase
                .invoke(
                        orderDTO.getFinalProducts(),
                        1L,
                        1000.0,
                        false,
                        transactionDTO,
                        10000.0,
                        1L
                )
                .join();

        Mockito.verify(orderBrandHistoryRepository)
                .saveAll(orderBrandHistoryEntities);

        assertTrue(result);
    }

    @Test
    void test_Create_Order_Brand_History_Total(){

        Mockito.when(orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities))
                .thenReturn(orderBrandHistoryEntities);

        Mockito.when(getBrandIdsByOrder.invoke(orderDTO.getFinalProducts()))
                .thenReturn(brandIds);
        orderEntity.setId(1L);
        Mockito.when(ordersRepository.findAllByTransactionIdOrderByCreatedAtAsc(1L))
                .thenReturn(List.of(orderEntity));
        Mockito.when(orderBrandHistoryRepository.findAllByOrderIdAndOrderStatusId(1L, 1L))
                .thenReturn(orderBrandHistoryEntities);

        Boolean result = createOrderBrandHistoryUseCase
                .invoke(
                        orderDTO.getFinalProducts(),
                        1L,
                        10000.0,
                        true,
                        transactionDTO,
                        10000.0,
                        1L
                )
                .join();

        Mockito.verify(orderBrandHistoryRepository,times(2))
                .saveAll(orderBrandHistoryEntities);

        assertTrue(result);
    }
    @Test
    void test_Create_Order_Brand_History_Total_Exception(){

        Mockito.when(orderBrandHistoryRepository.saveAll(orderBrandHistoryEntities))
                .thenReturn(orderBrandHistoryEntities);

        Mockito.when(getBrandIdsByOrder.invoke(orderDTO.getFinalProducts()))
                .thenReturn(brandIds);
        orderEntity.setId(1L);
        Mockito.when(ordersRepository.findAllByTransactionIdOrderByCreatedAtAsc(1L))
                .thenReturn(List.of());
        assertThrows(GenericOrderBcException.class,() -> {
            createOrderBrandHistoryUseCase
                    .invoke(
                            orderDTO.getFinalProducts(),
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
