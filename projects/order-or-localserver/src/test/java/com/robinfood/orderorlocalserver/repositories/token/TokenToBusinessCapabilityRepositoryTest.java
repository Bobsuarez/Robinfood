package com.robinfood.orderorlocalserver.repositories.token;


import com.robinfood.orderorlocalserver.configs.TokenToBusinessCapabilityProperties;
import com.robinfood.orderorlocalserver.entities.token.ResultResponseEntity;
import com.robinfood.orderorlocalserver.entities.token.TokenResponseEntity;
import com.robinfood.orderorlocalserver.network.SSOApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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

    @InjectMocks
    private TokenToBusinessCapabilityRepository repository;

    @Test
    void given_transaction_then_return_token_from_sso() throws IOException {
        // Arrange
        var minutes = 9;

        var dateToken = LocalDateTime.now().plusMinutes(minutes);

        var tokenResponse = TokenResponseEntity.builder()
            .code(200)
            .status("OK")
            .result(ResultResponseEntity.builder()
                .accessToken("access-token")
                .expiresIn(dateToken.toEpochSecond(OffsetDateTime.now().getOffset()))
                .build())
            .build();


        var responseEntity = new ResponseEntity<TokenResponseEntity>(tokenResponse, HttpStatus.OK);

        when(configurationProperties.getIssuer()).thenReturn("issuer");
        when(configurationProperties.getAuthKey()).thenReturn("auth_key");
        when(configurationProperties.getAuthSecret()).thenReturn("auth_secret");

        when(tokenToBusinessCapabilityApi.get(any())).thenReturn(responseEntity);
        //when(responseRetrofit.execute()).thenReturn(Response.success(tokenResponse));

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
