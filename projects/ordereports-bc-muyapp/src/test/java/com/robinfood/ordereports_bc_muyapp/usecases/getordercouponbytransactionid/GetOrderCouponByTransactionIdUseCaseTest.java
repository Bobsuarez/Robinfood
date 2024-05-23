package com.robinfood.ordereports_bc_muyapp.usecases.getordercouponbytransactionid;

import com.robinfood.ordereports_bc_muyapp.datamock.dto.ResponseCouponsDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderCouponEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderCouponDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseCouponsDTO;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.ordercoupons.IOrderCouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class GetOrderCouponByTransactionIdUseCaseTest {

    @Mock
    private IOrderCouponRepository getOrderCouponRepository;

    @Mock
    private OrderDetailOrderMapper orderDetailOrderMapper;

    @InjectMocks
    private GetOrderCouponByTransactionIdUseCase getOrderCouponByTransactionIdUseCase;

    @Test
    void test_GetOrderCouponByTransactionIdUseCase_When_IsPresent_Should_TransactionId_Return()
            throws ExecutionException, InterruptedException {

        Mockito.when(getOrderCouponRepository.findAllByTransactionId(anyInt()))
                .thenReturn(Optional.of(List.of(OrderCouponEntityMock.getDataDefault())));

        Mockito.when(orderDetailOrderMapper.mapFinalProductToResponseCouponDTO(any(OrderCouponDTO.class)))
                .thenReturn(ResponseCouponsDTOMock.getDataDefault());

        final CompletableFuture<List<ResponseCouponsDTO>> responseCouponsDTOFuture =
                getOrderCouponByTransactionIdUseCase.invoke(1);

        assertEquals(responseCouponsDTOFuture.get()
                             .get(0)
                             .getCode(), "Coupon");
    }
}