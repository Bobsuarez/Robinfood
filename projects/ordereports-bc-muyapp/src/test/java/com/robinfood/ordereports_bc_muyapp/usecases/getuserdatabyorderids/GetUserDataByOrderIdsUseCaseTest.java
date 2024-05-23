package com.robinfood.ordereports_bc_muyapp.usecases.getuserdatabyorderids;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.UserDataEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.UserDataDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderUserDataEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.UserDataMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orderuserdata.IOrderUserDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class GetUserDataByOrderIdsUseCaseTest {

    @Mock
    private IOrderUserDataRepository userDataRepository;

    @Mock
    private UserDataMapper userDataMapper;

    @InjectMocks
    private GetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    @Test
    void test_ValidatedStatus_When_IsPresent_Should_OrderStatusDTO_Return()
            throws ExecutionException, InterruptedException {

        Mockito.when(userDataRepository.findByOrderId(anyInt()))
                .thenReturn(Optional.of(UserDataEntityMock.getDataDefault()));

        Mockito.when(userDataMapper.toUserDataDTO(any(OrderUserDataEntity.class)))
                .thenReturn(UserDataDTO.builder()
                                    .id(1)
                                    .build());

        final CompletableFuture<UserDataDTO> resultOrderStatusDTO =
                getUserDataByOrderIdsUseCase.invoke(1);

        assertEquals(resultOrderStatusDTO.get()
                             .getId(), 1);
    }
}