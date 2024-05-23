package com.robinfood.orderorlocalserver.usecases.gettokenbusinesscapability;

import com.robinfood.orderorlocalserver.mocks.entities.TokenMock;
import com.robinfood.orderorlocalserver.repositories.redis.ITokenBusinessCapabilityRedisClientRepository;
import com.robinfood.orderorlocalserver.repositories.token.ITokenToBusinessCapabilityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTokenBusinessCapabilityTest {

    @Mock
    private ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository;

    @Mock
    private ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    @InjectMocks
    private GetTokenBusinessCapabilityUseCase useCase;

    @Test
    void given_transaction_then_return_token_to_business_capability() {
        // Arrange
        var token = TokenMock.build();

        when(tokenBusinessCapabilityRedisClientRepository.getToken()).thenReturn(Optional.empty());

        when(getTokenToBusinessCapabilityRepository.get()).thenReturn(token);

        // Act
        var response = useCase.invoke();

        // Assert
        assertNotNull(response);
        assertEquals(token.getAccessToken(), response.getAccessToken());
        assertEquals(token.getExpiresIn(), response.getExpiresIn());
    }

}
