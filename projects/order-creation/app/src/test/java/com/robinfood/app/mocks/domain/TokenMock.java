package com.robinfood.app.mocks.domain;

import com.robinfood.core.models.domain.Token;

public class TokenMock {

    private static final String ACCESS_TOKEN = "token";
    private static final Long EXPIRE_IN = 432432L;

    public static Token build() {
        return Token.builder()
            .accessToken(ACCESS_TOKEN)
            .expiresIn(EXPIRE_IN)
            .build();
    }

}
