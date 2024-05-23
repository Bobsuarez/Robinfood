package com.robinfood.ordereports_bc_muyapp.usecases.getcurrentstatusbyorderhistory;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderHistoryEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderHistoryDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderhistory.IOrderHistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCurrentStatusByOrderHistoryUseCaseTest {

    @Mock
    private IOrderHistoryRepository orderHistoryRepository;

    @InjectMocks
    private GetCurrentStatusByOrderHistoryUseCase useCase;

    @Test
    void test_ValidatedListOrderHistory_When_IsFull_Should_OrderHistory_Return()
            throws ExecutionException, InterruptedException {

        when(orderHistoryRepository.findAllByOrderId(anyInt()))
                .thenReturn(Collections.singletonList(OrderHistoryEntityMock.getDataDefault()));

        CompletableFuture<List<OrderHistoryDTO>> historyDTOS =
                useCase.getListOrderHistory(OrderHistoryEntityMock.getDataDefault()
                                                    .getOrderId());

        Assertions.assertEquals(1, historyDTOS.get()
                .size());
    }

    @Test
    void test_ValidatedListOrderHistory_When_NotFound_Should_OrderHistory_Return()
            throws ExecutionException, InterruptedException {

        when(orderHistoryRepository.findAllByOrderId(anyInt()))
                .thenReturn(null);

        CompletableFuture<List<OrderHistoryDTO>> historyDTOS =
                useCase.getListOrderHistory(OrderHistoryEntityMock.getDataDefault()
                                                    .getOrderId());

        Assertions.assertEquals(0, historyDTOS.get()
                .size());
    }


    @Test
    void test_ValidatedHistory_When_NotFound_Should_OrderHistory_Return()
            throws ExecutionException, InterruptedException {

        when(orderHistoryRepository.findAllByOrderId(anyInt()))
                .thenReturn(Collections.singletonList(OrderHistoryEntityMock.getDataDefault()));

        CompletableFuture<List<OrderHistoryDTO>> historyDTOS =
                useCase.getListOrderHistory(OrderHistoryEntityMock.getDataDefault()
                                                    .getOrderId());

        CompletableFuture<OrderHistoryDTO> orderHistoryDTO = useCase.invoke(historyDTOS.get(), (short) 1);

        Assertions.assertEquals(1123L, orderHistoryDTO.get()
                .getId());
    }

}