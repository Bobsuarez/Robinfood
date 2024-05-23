package com.robinfood.storeor.mocks;


import com.robinfood.storeor.entities.token.TokenModel;

public class TokenMock {

    private static final String ACCESS_TOKEN = "token";
    private static final Long EXPIRE_IN = 432432L;

    public static TokenModel build() {
        return TokenModel.builder()
            .accessToken(ACCESS_TOKEN)
            .expiresIn(EXPIRE_IN)
            .build();
    }

}
