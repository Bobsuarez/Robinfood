package com.robinfood.app.usecases.getlistpaymentmethods;

import com.robinfood.app.usecases.gettokenbusinesscapability.GetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.paymentmethods.PaymentMethodsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetListPaymentMethodsUseCaseTest {

    @Mock
    private GetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private PaymentMethodsRepository paymentMethodsRepository;

    @InjectMocks
    private GetListPaymentMethodsUseCase getListPaymentMethodsUseCase;

    @Test
    void Test_invoke_Should_ReturnOrderHistory_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(paymentMethodsRepository.getDataPaymentMethods(anyString()))
                .thenReturn(new Result.Success<>(List.of(PaymentMethodsFilterDTO.builder().build())));

        getListPaymentMethodsUseCase.invoke();

        verify(paymentMethodsRepository)
                .getDataPaymentMethods(
                        anyString()
                );
    }
}
