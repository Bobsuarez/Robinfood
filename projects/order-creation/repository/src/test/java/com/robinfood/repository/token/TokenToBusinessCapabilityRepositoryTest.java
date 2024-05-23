package com.robinfood.repository.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.robinfood.core.models.retrofit.ResultResponse;
import com.robinfood.core.models.retrofit.TokenResponse;
import com.robinfood.network.api.TokenToBusinessCapabilityAPI;
import com.robinfood.repository.config.properties.TokenToBusinessCapabilityProperties;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import retrofit2.Call;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class TokenToBusinessCapabilityRepositoryTest {

    @Mock
    private TokenToBusinessCapabilityAPI tokenToBusinessCapabilityApi;

    @Mock
    private TokenToBusinessCapabilityProperties configurationProperties;

    @Mock
    private Call<TokenResponse> responseRetrofit;

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

        when(configurationProperties.getIssuer()).thenReturn("issuer");
        when(configurationProperties.getAuthKey()).thenReturn("auth_key");
        when(configurationProperties.getAuthSecret()).thenReturn("auth_secret");

        when(tokenToBusinessCapabilityApi.get(any())).thenReturn(responseRetrofit);
        when(responseRetrofit.execute()).thenReturn(Response.success(tokenResponse));

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
