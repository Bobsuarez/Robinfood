package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.app.mocks.OrderHistoryItemEntityDTO;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test of GetOrderHistoryUseCase
 */
@ExtendWith(MockitoExtension.class)
class GetOrderHistoryUseCaseTest {

    @Mock
    private IOrderHistoryRepository orderHistoryRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private GetOrderHistoryUseCase getOrderHistoryUseCase;

    @Test
    void Test_invoke_Should_ReturnOrderHistory_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(orderHistoryRepository.getOrderHistory(any(OrderHistoryRequestDTO.class), anyString()))
                .thenReturn(new Result.Success<>(OrderHistoryItemEntityDTO.getDataDefault()));

        getOrderHistoryUseCase.invoke(OrderHistoryRequestDTO.builder().build());

        verify(orderHistoryRepository)
                .getOrderHistory(
                        any(OrderHistoryRequestDTO.class),
                        anyString()
                );
    }

}