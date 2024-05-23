package com.robinfood.repository.token;

import com.robinfood.entities.TokenEntity;
import com.robinfood.entities.request.TokenRequestEntity;
import com.robinfood.network.http.api.SsoAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TokenToBusinessCapabilityRepositoryTest {

    @Mock
    private SsoAPI ssoAPI;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Get_Should_ReturnTokenEntity_When_InvokeTheRepository() {
        when(ssoAPI.getToken(any(TokenRequestEntity.class)))
                .thenReturn(TokenEntity.builder().build());

        TokenToBusinessCapabilityRepository tokenToBusinessCapabilityRepository =
                new TokenToBusinessCapabilityRepository(ssoAPI);

        tokenToBusinessCapabilityRepository.get();

        verify(ssoAPI, Mockito.times(1))
                .getToken(any(TokenRequestEntity.class));
    }

    @Test
    void test_TokenToBusinessCapabilityRepository_Should_BuildConstructor_When_MethodInvoke() {

        TokenToBusinessCapabilityRepository tokenToBusinessCapabilityRepository =
                new TokenToBusinessCapabilityRepository();

        Assertions.assertNotNull(tokenToBusinessCapabilityRepository);
    }
}