package com.robinfood.app.usecases.getstores;

import com.robinfood.app.mocks.StoreDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.store.IStoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetStoresUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IStoreRepository storeRepository;

    @InjectMocks
    private GetStoresUseCase getConfigStoreUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(storeRepository.getStores(anyString()))
                .thenReturn(new Result.Success<>(List.of(StoreDTOMock.getDataDefault())));

        getConfigStoreUseCase.invoke();
        verify(storeRepository).getStores(anyString());
    }
}
