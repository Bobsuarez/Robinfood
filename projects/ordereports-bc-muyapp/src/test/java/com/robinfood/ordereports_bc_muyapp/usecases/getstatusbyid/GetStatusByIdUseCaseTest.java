package com.robinfood.ordereports_bc_muyapp.usecases.getstatusbyid;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.StatusEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderStatusDTO;
import com.robinfood.ordereports_bc_muyapp.repository.status.IStatusRepository;
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
class GetStatusByIdUseCaseTest {

    @Mock
    private IStatusRepository statusRepository;

    @InjectMocks
    private GetStatusByIdUseCase getStatusByIdUseCase;

    @Test
    void test_ValidatedStatus_When_IsPresent_Should_OrderStatusDTO_Return() throws ExecutionException, InterruptedException {

        Mockito.when(statusRepository.findByIdIn(anyList()))
                .thenReturn(List.of(StatusEntityMock.getDataDefault()));

        final CompletableFuture<List<OrderStatusDTO>> resultOrderStatusDTO =
                getStatusByIdUseCase.invoke(List.of((short) 1));

        assertEquals(resultOrderStatusDTO.get()
                             .getFirst()
                             .getId(), (short) 1);
    }
}