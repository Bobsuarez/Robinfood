package com.robinfood.app.usecases.getbrands;

import com.robinfood.app.mocks.configurations.BrandsDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.brands.IBrandsRepository;
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
public class GetBrandsUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IBrandsRepository brandsRepository;

    @InjectMocks
    private GetBrandsUseCase getBrandsUseCase ;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {
        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(brandsRepository.getAll(anyString())).thenReturn(new Result.Success<>(BrandsDTOMock.getDataDefault()));

        getBrandsUseCase.invoke();

        verify(brandsRepository).getAll(anyString());
    }

    @Test
    void Test_invoke_Should_Return_Error_When_InvokeTheUseCase() {
        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(brandsRepository.getAll(anyString()))
                .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(Exception.class, () -> {
            getBrandsUseCase.invoke();
        });

        verify(brandsRepository).getAll(anyString());
    }
}
