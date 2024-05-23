package com.robinfood.ordereports_bc_muyapp.usecases.getplatformbyorderid;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderDeviceEntityMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderEntityMock;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderDeviceEntity;
import com.robinfood.ordereports_bc_muyapp.repository.orderdevice.IOrderDeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPlatformByOrderIdUseCaseTest {

    @Mock
    private IOrderDeviceRepository orderDeviceRepository;

    @InjectMocks
    private GetPlatformByOrderIdUseCase getPlatformByOrderIdUseCase;

    @Test
    void test_ValidatedOrderPayment_When_IsPresentOrder_Should_PlatformId_Return() throws ExecutionException, InterruptedException {

        when(orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(anyInt()))
                .thenReturn(OrderDeviceEntityMock.getDataDefault());

        CompletableFuture<Short> orderPaymentDTOS =
                getPlatformByOrderIdUseCase.invoke(OrderEntityMock.getDataDefault()
                                                           .getId());

        assertEquals(
                OrderDeviceEntityMock.getDataDefault()
                        .getPlatformId(),
                orderPaymentDTOS.get()
        );
    }

    @Test
    void test_ValidatedOrderPayment_When_IsNotPresentOrder_Should_PlatformIdDefault_Return() throws ExecutionException, InterruptedException {

        when(orderDeviceRepository.findFirstByOrderIdOrderByIdDesc(anyInt()))
                .thenReturn(OrderDeviceEntity.builder()
                                    .build());

        CompletableFuture<Short> orderPaymentDTOS =
                getPlatformByOrderIdUseCase.invoke(OrderEntityMock.getDataDefault()
                                                           .getId());

        assertEquals((short) 2, orderPaymentDTOS.get());
    }
}