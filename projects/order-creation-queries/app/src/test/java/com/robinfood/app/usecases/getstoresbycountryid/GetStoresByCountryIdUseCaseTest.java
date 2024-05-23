package com.robinfood.app.usecases.getstoresbycountryid;

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
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetStoresByCountryIdUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IStoreRepository storeRepository;

    @InjectMocks
    private GetStoresByCountryIdUseCase getStoresByCountryIdUseCase;

    @Test
    void test_Invoke_Should_Return_When_InvokeTheUseCase() {

        final String token = "token";
        final Long countryId = 1L;

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(storeRepository.getStores(anyString()))
                .thenReturn(new Result.Success<>(List.of(StoreDTOMock.getDataDefault())));

        getStoresByCountryIdUseCase.invoke(countryId);
        verify(storeRepository).getStores(anyString());
    }

    @Test
    void test_Invoke_Should_Return_When_Exception() {

        try {
            final String token = "token";
            final Long countryId = 1L;

            when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                    TokenModel.builder()
                            .accessToken(token)
                            .build()
            );

            when(storeRepository.getStores(anyString()))
                    .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

            getStoresByCountryIdUseCase.invoke(countryId);
            verify(storeRepository).getStores(anyString());

        } catch (Exception e) {
            assertEquals(
                    "class org.springframework.web.server.ResponseStatusException",
                    e.getClass().toString()
            );
        }
    }
}
