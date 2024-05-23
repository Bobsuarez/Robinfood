package com.robinfood.localorderbc.mocks.token;

import com.robinfood.localorderbc.entities.security.ResponseDataOkSSOAuthEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOAuthEntity;

public class ResponseSSOAuthEntityMocks {

    private final String TOKEN_ACCESS_AUTH = "TOKEN_ACCESS_AUTH";

    public final ResponseDataOkSSOAuthEntity responseDataOkSSOAuthEntity = ResponseDataOkSSOAuthEntity
            .builder()
            .accessToken(TOKEN_ACCESS_AUTH)
            .authExpiration(123456L)
            .build();

    public final ResponseSSOAuthEntity responseSSOAuthEntitySuccess = ResponseSSOAuthEntity
            .builder()
            .code(200)
            .result(responseDataOkSSOAuthEntity)
            .status("OK")
            .build();
}
