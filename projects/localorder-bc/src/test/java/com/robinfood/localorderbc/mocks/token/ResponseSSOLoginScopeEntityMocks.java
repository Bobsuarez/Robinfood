package com.robinfood.localorderbc.mocks.token;


import com.robinfood.localorderbc.entities.security.ResponseDataOkSSOLoginScopeEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOLoginScopeEntity;
import com.robinfood.localorderbc.entities.security.TokenPayloadSSOLoginScopeEntity;
import com.robinfood.localorderbc.entities.security.TokenSSOLoginScopeEntity;

public class ResponseSSOLoginScopeEntityMocks {

    public final TokenPayloadSSOLoginScopeEntity tokenPayloadSSOLoginScopeEntity = TokenPayloadSSOLoginScopeEntity
            .builder()
            .refreshToken("refreshtoken")
            .refreshExpiresIn(123456L)
            .build();

    public final TokenSSOLoginScopeEntity tokenSSOLoginScopeEntity = TokenSSOLoginScopeEntity
            .builder()
            .token(tokenPayloadSSOLoginScopeEntity)
            .build();

    public final ResponseDataOkSSOLoginScopeEntity responseDataOkSSOLoginScopeEntity = ResponseDataOkSSOLoginScopeEntity
            .builder()
            .token(tokenSSOLoginScopeEntity)
            .build();

    public final ResponseSSOLoginScopeEntity responseSSOLoginScopeEntitySuccess = ResponseSSOLoginScopeEntity
            .builder()
            .code("200")
            .status("OK")
            .result(responseDataOkSSOLoginScopeEntity)
            .build();
}
