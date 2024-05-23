package com.robinfood.changestatusor.usecases.gettokenbusinesscapability;

import com.robinfood.changestatusor.models.domain.Token;
import com.robinfood.changestatusor.repository.redis.ITokenBusinessCapabilityRedisClientRepository;
import com.robinfood.changestatusor.repository.token.ITokenToBusinessCapabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GetTokenBusinessCapabilityUseCaseTest {

    @Mock
    ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository;

    @Mock
    ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    @InjectMocks
    GetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInvokeWithExistingTokenInRedis() {
        when(getTokenToBusinessCapabilityRepository.get()).thenReturn(
               Token.builder().build()
        );

        Token token = getTokenBusinessCapabilityUseCase.invoke();

        assertNotNull(token);
    }

    @Test
    public void testInvokeWithoutExistingTokenInRedis() {

        when(tokenBusinessCapabilityRedisClientRepository.getToken()).thenReturn(Optional.empty());

        when(getTokenToBusinessCapabilityRepository.get()).thenReturn(Token.builder().build());

        Token token = getTokenBusinessCapabilityUseCase.invoke();

        assertNotNull(token);
    }
}
