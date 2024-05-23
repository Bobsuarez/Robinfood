package com.robinfood.localorderbc.repositories.redis;

import com.robinfood.localorderbc.entities.redis.TokenOrchestratorRedisEntity;
import com.robinfood.localorderbc.entities.token.TokenModel;
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
class TokenOrchestratorRedisClientRepositoryTest {

    private static final String REDIS_ID = "id";

    @Mock
    private ITokenOrchestratorRedisRepository tokenBusinessCapabilityRedisRepository;

    @InjectMocks
    private TokenOrchestratorRedisClientRepository repository;


    @Test
    void given_transaction_then_return_token_from_redis() {

        // Arrange
        when(tokenBusinessCapabilityRedisRepository.findById(anyString())).thenReturn(
            Optional.of(TokenOrchestratorRedisEntity.builder()
                .token(TokenModel.builder().build())
                .build()));

        //when(configurationProperties.getRedisId()).thenReturn(REDIS_ID);

        // Act
        Optional<TokenModel> response = repository.getToken(REDIS_ID);

        // Assert
        assertTrue(response.isPresent());
    }

    @Test
    void given_transaction_then_set_token_on_redis() {

        // Arrange
        TokenModel token = TokenModel.builder()
            .accessToken("access-token")
            .expiresIn(12345L)
            .build();

        //when(configurationProperties.getRedisId()).thenReturn(REDIS_ID);

        // Act
        repository.setToken(token,REDIS_ID);

        // Assert
        verify(tokenBusinessCapabilityRedisRepository, times(1)).save(any());
    }

    @Test
    void given_transaction_then_delete_token_on_redis() {

        // Act
        repository.deleteAllKeyRedis();

        // Assert
        verify(tokenBusinessCapabilityRedisRepository, times(1)).deleteAll();
    }

}
