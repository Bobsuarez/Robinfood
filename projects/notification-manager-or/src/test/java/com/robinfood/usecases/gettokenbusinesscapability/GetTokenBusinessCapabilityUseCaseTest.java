package com.robinfood.usecases.gettokenbusinesscapability;

import com.robinfood.entities.TokenEntity;
import com.robinfood.repository.token.ITokenToBusinessCapabilityRepository;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class GetTokenBusinessCapabilityUseCaseTest {

    @Mock
    private ITokenToBusinessCapabilityRepository tokenToBusinessCapabilityRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_ReturnToken_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        when(tokenToBusinessCapabilityRepository.get())
                .thenReturn(TokenEntity.builder().build());

        GetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase =
                new GetTokenBusinessCapabilityUseCase(tokenToBusinessCapabilityRepository);

        getTokenBusinessCapabilityUseCase.invoke();

        Mockito.verify(tokenToBusinessCapabilityRepository, Mockito.times(1))
                .get();

        clearAllCaches();
    }
}