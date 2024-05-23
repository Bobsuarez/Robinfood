package com.robinfood.ordereports_bc_muyapp.usecases.getorderproducttaxesbyfinalproductid;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderProductTaxEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderProductTaxDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderproducttaxes.IOrderProductTaxesRepository;
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
import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
class GetOrderProductTaxesByFinalProductIdUseCaseTest {


    @Mock
    private IOrderProductTaxesRepository orderProductTaxesRepository;


    @InjectMocks
    private GetOrderProductTaxesByFinalProductIdUseCase getOrderProductTaxesByFinalProductIdUseCase;

    @Test
    void test_ValidatedStatus_When_IsPresent_Should_OrderProductTaxes_Return()
            throws ExecutionException, InterruptedException {

        Mockito.when(orderProductTaxesRepository.findByOrderFinalProductIdIn(anyList()))
                .thenReturn(Optional.of(List.of(OrderProductTaxEntityMock.getDataDefault())));

        final CompletableFuture<List<OrderProductTaxDTO>> resultOrderStatusDTO =
                getOrderProductTaxesByFinalProductIdUseCase.invoke(List.of(1L, 2L));

        assertEquals(resultOrderStatusDTO.get()
                             .size(), 1L);
    }
}