package com.robinfood.ordereports_bc_muyapp.usecases.getorderbyuid;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderByUIdUseCaseTest {

    @Mock
    private OrderDetailOrderMapper orderDetailOrderMapper;

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private GetOrderByOrderIdUseCase getOrderByUIdUseCase;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
                getOrderByUIdUseCase,
                "notStatusIds",
                List.of(9L)
        );
    }

    @Test
    void test_ValidatedListOrderEntity_When_IsFull_Should_OrderHistory_Return()
            throws ExecutionException, InterruptedException {

        when(ordersRepository.findByTransactionIdAndStatusIdNotIn(anyInt(), anyList()))
                .thenReturn(OrderEntityMock.getDataDefault());

        when(orderDetailOrderMapper.mapOrderEntityToResponseDTO(any(OrderEntity.class)))
                .thenReturn(ResponseOrderDetailDTO.builder()
                                    .id(1)
                                    .build());

        CompletableFuture<ResponseOrderDetailDTO> responseOrderDetailDTO =
                getOrderByUIdUseCase.invoke(12345678);

        assertEquals(1, responseOrderDetailDTO.get()
                .getId());
    }

    @Test
    void test_ValidatedListOrderEntity_When_IsNull_Should_ApplicationException_Return() {

        when(ordersRepository.findByTransactionIdAndStatusIdNotIn(anyInt(), anyList()))
                .thenReturn(null);

        // Act & Assert
        CompletableFuture<ResponseOrderDetailDTO> future = getOrderByUIdUseCase.invoke(123456);
        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertInstanceOf(TransactionExecutionException.class, exception.getCause());
        assertEquals("Transaction creation failed", exception.getCause()
                .getMessage());
    }
}