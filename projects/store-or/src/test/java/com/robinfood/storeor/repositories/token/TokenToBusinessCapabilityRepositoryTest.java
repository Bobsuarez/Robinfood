package com.robinfood.storeor.repositories.token;


import com.robinfood.storeor.configs.TokenToBusinessCapabilityProperties;
import com.robinfood.storeor.configs.apis.SSOApi;
import com.robinfood.storeor.entities.token.ResultResponse;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.entities.token.TokenResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static com.robinfood.storeor.configs.constants.APIConstants.AUTHORIZATION_HEADER_BEARER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenToBusinessCapabilityRepositoryTest {

    @Mock
    private SSOApi tokenToBusinessCapabilityApi;

    @Mock
    private TokenToBusinessCapabilityProperties configurationProperties;

    @Mock
    private TokenResponse responseRetrofit;

    @InjectMocks
    private TokenToBusinessCapabilityRepository repository;

    @Test
    void given_transaction_then_return_token_from_sso() throws IOException {

        // Arrange
        var minutes = 9;

        var dateToken = LocalDateTime.now().plusMinutes(minutes);

        var tokenResponse = TokenResponse.builder()
            .code(200)
            .status("OK")
            .result(ResultResponse.builder()
                .accessToken("access-token")
                .expiresIn(dateToken.toEpochSecond(OffsetDateTime.now().getOffset()))
                .build())
            .build();

        var tet = TokenModel.builder()
                .accessToken(AUTHORIZATION_HEADER_BEARER
                        .concat(tokenResponse.getResult().getAccessToken()))
                .expiresIn(9L)
                .build();

        when(configurationProperties.getIssuer()).thenReturn("issuer");
        when(configurationProperties.getAuthKey()).thenReturn("auth_key");
        when(configurationProperties.getAuthSecret()).thenReturn("auth_secret");

        when(tokenToBusinessCapabilityApi.get(any())).thenReturn(tokenResponse);

        // Act
        var response = repository.get();

        // Assert
        assertNotNull(response);
        assertEquals(minutes - 1, response.getExpiresIn());
        assertEquals(
            "Bearer ".concat(tokenResponse.getResult().getAccessToken()), response.getAccessToken()
        );
    }

}
