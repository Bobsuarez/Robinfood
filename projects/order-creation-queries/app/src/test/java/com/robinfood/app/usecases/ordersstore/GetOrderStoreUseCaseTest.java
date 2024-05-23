package com.robinfood.app.usecases.ordersstore;

import com.robinfood.app.mocks.OrderStoreDTOMock;
import com.robinfood.app.usecases.getordersstore.GetOrdersStoreUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.ordersstore.DataOrderStoreRequestDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.ordersstore.IOrdersStoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderStoreUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    @Mock
    private IOrdersStoreRepository ordersStoreRepository;

    @InjectMocks
    private GetOrdersStoreUseCase getOrdersStoreUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {
        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(ordersStoreRepository.getDataOrderStore(any(DataOrderStoreRequestDTO.class), anyString()))
                .thenReturn(new Result.Success<>(OrderStoreDTOMock.getDataDefault()));

        getOrdersStoreUseCase.invoke(DataOrderStoreRequestDTO.builder().build());

        verify(ordersStoreRepository)
                .getDataOrderStore(
                        any(DataOrderStoreRequestDTO.class),
                        anyString()
                );
    }
}
