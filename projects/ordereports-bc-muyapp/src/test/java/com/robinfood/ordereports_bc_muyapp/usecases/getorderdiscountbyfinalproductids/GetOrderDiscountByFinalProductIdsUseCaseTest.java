package com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyfinalproductids;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderDiscountEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderdiscount.IOrderDiscountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
class GetOrderDiscountByFinalProductIdsUseCaseTest {

    @Mock
    private IOrderDiscountRepository orderDiscountRepository;

    @InjectMocks
    private GetOrderDiscountByFinalProductIdsUseCase getOrderDiscountByFinalProductIdsUseCase;

    @Test
    void test_GetOrderDiscountByFinalProductIdsUseCase_When_IsPresent_Should_TransactionId_Return()
            throws ExecutionException, InterruptedException {

        Mockito.when(orderDiscountRepository.findOrderDiscountEntitiesByOrderFinalProductIdIn(anyList()))
                .thenReturn(List.of(OrderDiscountEntityMock.getDataDefault()));

        final CompletableFuture<List<OrderDiscountDTO>> responseCouponsDTOFuture =
                getOrderDiscountByFinalProductIdsUseCase.invoke(List.of(1L, 2L));

        assertEquals(responseCouponsDTOFuture.get().size(), 1);
    }
}