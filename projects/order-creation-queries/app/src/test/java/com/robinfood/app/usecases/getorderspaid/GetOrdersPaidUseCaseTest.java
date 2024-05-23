package com.robinfood.app.usecases.getorderspaid;

import com.robinfood.app.mocks.GetOrderPaidDTOMock;
import com.robinfood.app.mocks.OrdersPaidResponseDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderspaid.IOrdersPaidRepository;
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
public class GetOrdersPaidUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IOrderPaidResponseUseCase orderPaidResponseUseCase;

    @Mock
    private IOrdersPaidRepository ordersPaidRepository;

    @InjectMocks
    private GetOrdersPaidUseCase getOrdersPaidUseCas;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(ordersPaidRepository.getDataOrdersPaid(
                any(DataOrdersPaidRequestDTO.class),
                anyString())
        ).thenReturn(new Result.Success<>(GetOrderPaidDTOMock.getDataDefault()));

        when(orderPaidResponseUseCase.invoke(any(GetOrdersPaidDTO.class), anyString()))
                .thenReturn(OrdersPaidResponseDTOMock.getDataDefault());

        getOrdersPaidUseCas.invoke(DataOrdersPaidRequestDTO.builder().build());

        verify(ordersPaidRepository)
                .getDataOrdersPaid(
                        any(DataOrdersPaidRequestDTO.class),
                        anyString()
                );

        verify(orderPaidResponseUseCase).invoke(any(GetOrdersPaidDTO.class), anyString());
    }
}
