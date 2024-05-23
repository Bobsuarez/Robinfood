package com.robinfood.localserver.commons.dtos.security;

import com.robinfood.localserver.commons.entities.token.TokenModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseSSOLoginScopeDTO {

    private String status;

    private String code;

    private ResponseDataOkSSOLoginScopeDTO result;

    public TokenModel toDomainWithExpirationMinute(Long minutes) {
        return TokenModel.builder()
                .accessToken(result.getToken().getToken().getRefreshToken())
                .expiresIn(minutes)
                .build();
    }
}
