package com.robinfood.app.usecases.getlambdaexchange;

import com.robinfood.app.mocks.LambdaExchangeJsonObjectMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.lambdaexchange.ILambdaExchangeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetLambdaExchangeUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private ILambdaExchangeRepository lambdaExchangeRepository;

    @InjectMocks
    private GetLambdaExchangeUseCase getLambdaExchangeUseCase ;

    @Test
    void Test_invoke_Should_Return_OK_When_InvokeTheUseCase() {
        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(lambdaExchangeRepository.getExchanges(
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(new Result.Success<>(LambdaExchangeJsonObjectMock.getDataDefault()));

        getLambdaExchangeUseCase.invoke("2023/03/14", "2023/03/7");

        verify(lambdaExchangeRepository)
                .getExchanges(
                        anyString(),
                        anyString(),
                        anyString()
                );
    }

    @Test
    void Test_invoke_Should_Return_Error_When_InvokeTheUseCase() {
        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(lambdaExchangeRepository.getExchanges(
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.INTERNAL_SERVER_ERROR));

        getLambdaExchangeUseCase.invoke("2023/03/14", "2023/03/7");

        verify(lambdaExchangeRepository)
                .getExchanges(
                        anyString(),
                        anyString(),
                        anyString()
                );
    }
}
