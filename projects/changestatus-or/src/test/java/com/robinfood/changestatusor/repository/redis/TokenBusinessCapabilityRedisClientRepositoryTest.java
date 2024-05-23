package com.robinfood.changestatusor.repository.redis;

import com.robinfood.changestatusor.config.properties.TokenToBusinessCapabilityProperties;
import com.robinfood.changestatusor.entities.redis.TokenBusinessCapabilityRedis;
import com.robinfood.changestatusor.models.domain.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenBusinessCapabilityRedisClientRepositoryTest {

    private static final String REDIS_ID = "id";

    @Mock
    private ITokenBusinessCapabilityRedisRepository tokenBusinessCapabilityRedisRepository;

    @Mock
    private TokenToBusinessCapabilityProperties configurationProperties;

    @InjectMocks
    private TokenBusinessCapabilityRedisClientRepository repository;

    @Test
    void given_transaction_then_return_token_from_redis() {
        // Arrange
        when(tokenBusinessCapabilityRedisRepository.findById(anyString())).thenReturn(
                Optional.of(TokenBusinessCapabilityRedis.builder()
                        .token(Token.builder().build())
                        .build()));

        when(configurationProperties.getRedisId()).thenReturn(REDIS_ID);

        // Act
        var response = repository.getToken();

        // Assert
        assertTrue(response.isPresent());
    }

    @Test
    void given_transaction_then_return_error_from_redis() {

        // Arrange
        when(tokenBusinessCapabilityRedisRepository.findById(anyString())).thenReturn(Optional.empty());
        // Act
        var response = repository.getToken();
        // Assert
        assertTrue(!response.isPresent());
    }

    @Test
    void given_transaction_then_set_token_on_redis() {
        // Arrange
        var token = Token.builder()
                .accessToken("access-token")
                .expiresIn(12345L)
                .build();

        when(configurationProperties.getRedisId()).thenReturn(REDIS_ID);

        // Act
        repository.setToken(token);

        // Assert
        verify(tokenBusinessCapabilityRedisRepository, times(1)).save(any());
    }

    @Test
    void given_transaction_then_set_token_on_redis_error() {

        // Arrange
        var token = Token.builder()
                .accessToken("access-token")
                .expiresIn(12345L)
                .build();
        when(configurationProperties.getRedisId()).thenReturn(REDIS_ID);
        when(tokenBusinessCapabilityRedisRepository.save(any())).thenReturn(Error.class);
        // Act
        repository.setToken(token);
        // Assert
        verify(tokenBusinessCapabilityRedisRepository, times(1)).save(any());
    }
}
