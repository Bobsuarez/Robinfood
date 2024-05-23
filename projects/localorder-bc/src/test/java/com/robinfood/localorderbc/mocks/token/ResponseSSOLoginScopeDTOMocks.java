package com.robinfood.localorderbc.mocks.token;


import com.robinfood.localorderbc.dtos.security.ResponseDataOkSSOLoginScopeDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOLoginScopeDTO;
import com.robinfood.localorderbc.dtos.security.TokenPayloadSSOLoginScopeDTO;
import com.robinfood.localorderbc.dtos.security.TokenSSOLoginScopeDTO;

public class ResponseSSOLoginScopeDTOMocks {


    public final TokenPayloadSSOLoginScopeDTO tokenPayloadSSOLoginScopeDTO = TokenPayloadSSOLoginScopeDTO
            .builder()
            .refreshToken("refreshtoken")
            .refreshExpiresIn(123456L)
            .build();

    public final TokenSSOLoginScopeDTO tokenSSOLoginScopeEDTO= TokenSSOLoginScopeDTO
            .builder()
            .token(tokenPayloadSSOLoginScopeDTO)
            .build();

    public final ResponseDataOkSSOLoginScopeDTO responseDataOkSSOLoginScopeDTO = ResponseDataOkSSOLoginScopeDTO
            .builder()
            .token(tokenSSOLoginScopeEDTO)
            .build();

    public final ResponseSSOLoginScopeDTO responseSSOLoginScopeDTOSuccess = ResponseSSOLoginScopeDTO
            .builder()
            .code("200")
            .status("OK")
            .result(responseDataOkSSOLoginScopeDTO)
            .build();
}
