package com.robinfood.orderorlocalserver.mocks.entities;

import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;

public class TokenMock {

    private static final String ACCESS_TOKEN = "token";
    private static final Long EXPIRE_IN = 1L;

    public static TokenModelEntity build() {
        return TokenModelEntity.builder()
            .accessToken(ACCESS_TOKEN)
            .expiresIn(EXPIRE_IN)
            .build();
    }

}
