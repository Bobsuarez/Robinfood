package com.robinfood.ordereports_bc_muyapp.usecases.getorderaddressbyorderid;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderAddressEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderAddressDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderaddress.IOrderAddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderAddressByOrderIdUseCaseTest {

    @Mock
    private IOrderAddressRepository orderAddressRepository;

    @InjectMocks
    private GetOrderAddressByOrderIdUseCase getorderAddressByOrderIdUseCase;

    @Test
    void test_ValidatedOrdersAddress_When_FindById_Should_OrderAddress_Return()
            throws ExecutionException, InterruptedException {

        when(orderAddressRepository.findById(anyInt()))
                .thenReturn(Optional.of(OrderAddressEntityMock.getDataDefault()));

        CompletableFuture<OrderAddressDTO> orderAddressDTO = getorderAddressByOrderIdUseCase.invoke(1);

        Assertions.assertEquals(1, orderAddressDTO.get()
                .getOrderId());
    }
}