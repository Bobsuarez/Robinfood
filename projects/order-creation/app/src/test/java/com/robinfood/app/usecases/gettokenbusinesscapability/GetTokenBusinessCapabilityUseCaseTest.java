package com.robinfood.app.usecases.gettokenbusinesscapability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.domain.TokenMock;
import com.robinfood.repository.redis.ITokenBusinessCapabilityRedisClientRepository;
import com.robinfood.repository.token.ITokenToBusinessCapabilityRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
