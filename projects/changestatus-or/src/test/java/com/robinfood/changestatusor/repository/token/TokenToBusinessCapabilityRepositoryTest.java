package com.robinfood.changestatusor.repository.token;

import com.robinfood.changestatusor.config.network.api.token.TokenToBusinessCapabilityAPI;
import com.robinfood.changestatusor.config.properties.TokenToBusinessCapabilityProperties;
import com.robinfood.changestatusor.models.domain.Token;
import com.robinfood.changestatusor.models.retrofit.ResultResponse;
import com.robinfood.changestatusor.models.retrofit.TokenRequest;
import com.robinfood.changestatusor.models.retrofit.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenToBusinessCapabilityRepositoryTest {

    @Mock
    private TokenToBusinessCapabilityAPI tokenToBusinessCapabilityApi;

    @Mock
    private TokenToBusinessCapabilityProperties configurationProperties;

    @InjectMocks
    private TokenToBusinessCapabilityRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_Get_Token() {

        when(configurationProperties.getAuthKey()).thenReturn("mockKey");
        when(configurationProperties.getAuthSecret()).thenReturn("mockSecret");
        when(configurationProperties.getIssuer()).thenReturn("mockIssuer");

        LocalDateTime localDateTime = LocalDateTime.now();

        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();

        TokenResponse expectedResponse = TokenResponse.builder()
                .status("200")
                .result(ResultResponse.builder()
                        .expires_in(date)
                        .access_token("Test Token")
                        .build())
                .build();

        when(tokenToBusinessCapabilityApi.get(any(TokenRequest.class))).thenReturn(expectedResponse);

        repository = new TokenToBusinessCapabilityRepository(tokenToBusinessCapabilityApi, configurationProperties);

        Token token = repository.get();

        assertNotNull(token);
    }


}
