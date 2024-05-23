package com.robinfood.app.usecases.getorderpayment;

import com.robinfood.app.mocks.OrderPaymentDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderpayment.DataOrderPaymentRequestDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
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
class GetPosOrderPaymentUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IOrderPaymentRepository iOrderPaymentRepository;

    @InjectMocks
    private GetOrderPaymentUseCase getOrderPaymentUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(iOrderPaymentRepository.getDataOrderPayment(any(DataOrderPaymentRequestDTO.class), anyString()))
                .thenReturn(new Result.Success<>(OrderPaymentDTOMock.getDataDefault()));

        getOrderPaymentUseCase.invoke(DataOrderPaymentRequestDTO.builder().build());

        verify(iOrderPaymentRepository)
                .getDataOrderPayment(
                        any(DataOrderPaymentRequestDTO.class),
                        anyString()
                );
    }
}
