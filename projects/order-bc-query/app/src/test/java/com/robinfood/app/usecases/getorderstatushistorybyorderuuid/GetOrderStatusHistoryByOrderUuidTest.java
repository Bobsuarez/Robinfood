package com.robinfood.app.usecases.getorderstatushistorybyorderuuid;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.datamocks.entity.OrderHistoryEntityMock;
import com.robinfood.app.datamocks.entity.StatusEntityMock;
import com.robinfood.core.dtos.orderstatushistory.OrderStatusHistoryDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.status.IStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetOrderStatusHistoryByOrderUuidTest {

    @Mock
    private IOrderHistoryRepository orderHistoryRepository;

    @Mock
    private IOrdersRepository ordersRepository;

    @Mock
    private IStatusRepository statusRepository;

    @InjectMocks
    private GetOrderStatusHistoryByOrderUuidUseCase getOrderStatusHistoryByOrderUuidUseCase;

    @Test
    public void test_GetOrderStatusHistory_When_Success() throws ResourceNotFoundException {

        final Long orderId = 1L;
        final String orderUuid = "50eaf34f-7252-46ef-9a69-2225b06e14be";
        final List<Long> statusIds = List.of(1L);

        lenient().when(orderHistoryRepository.findAllByOrderId(orderId)).thenReturn(OrderHistoryEntityMock.getDataDefaultList());
        lenient().when(ordersRepository.findByUuid(orderUuid)).thenReturn(Optional.of(OrderEntityMock.getDataDefault()));
        lenient().when(statusRepository.findAllByIdIn(statusIds)).thenReturn(StatusEntityMock.getDataDefaultList());

        final List<OrderStatusHistoryDTO> response = getOrderStatusHistoryByOrderUuidUseCase.invoke(orderUuid);

        assertNotNull(response);
    }

    @Test
    public void test_GetOrderStatusHistory_When_Order_Not_Found() {

        final Long orderId = 1L;
        final String orderUuid = "50eaf34f-7252-46ef-9a69-2225b06e14be";
        final List<Long> statusIds = List.of(1L);

        lenient().when(orderHistoryRepository.findAllByOrderId(orderId)).thenReturn(OrderHistoryEntityMock.getDataDefaultList());
        lenient().when(ordersRepository.findByUuid(orderUuid)).thenReturn(Optional.of(OrderEntityMock.getDataDefault()));
        lenient().when(statusRepository.findAllByIdIn(statusIds)).thenReturn(StatusEntityMock.getDataDefaultList());

        assertThrows(ResponseStatusException.class, () -> getOrderStatusHistoryByOrderUuidUseCase.invoke(anyString()));
    }

    @Test
    public void test_GetOrderStatusHistory_When_Status_Not_Found() {

        final Long orderId = 1L;
        final String orderUuid = "50eaf34f-7252-46ef-9a69-2225b06e14be";
        final List<Long> statusIds = List.of(2L);

        lenient().when(orderHistoryRepository.findAllByOrderId(orderId)).thenReturn(OrderHistoryEntityMock.getDataDefaultList());
        lenient().when(ordersRepository.findByUuid(orderUuid)).thenReturn(Optional.of(OrderEntityMock.getDataDefault()));
        lenient().when(statusRepository.findAllByIdIn(statusIds)).thenReturn(StatusEntityMock.getDataDefaultList());

        assertThrows(ResponseStatusException.class, () -> getOrderStatusHistoryByOrderUuidUseCase.invoke(orderUuid));
    }
}
