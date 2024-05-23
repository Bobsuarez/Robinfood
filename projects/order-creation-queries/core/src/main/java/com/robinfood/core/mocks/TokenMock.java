package com.robinfood.core.mocks;

import com.robinfood.core.models.domain.TokenModel;

public class TokenMock {

    private static final String ACCESS_TOKEN = "token";
    private static final Long EXPIRE_IN = 1L;

    public static TokenModel build() {
        return TokenModel.builder()
            .accessToken(ACCESS_TOKEN)
            .expiresIn(EXPIRE_IN)
            .build();
    }

}
