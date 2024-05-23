package com.robinfood.localserver.commons.dtos.security;

import com.robinfood.localserver.commons.entities.token.TokenModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.robinfood.localserver.commons.constants.GlobalConstants.AUTHORIZATION_HEADER_BEARER;

@AllArgsConstructor
@Builder
@Data
public class ResponseSSOAuthDTO {

    private int code;

    private ResponseDataOkSSOAuthDTO result;

    private String status;

    public TokenModel toDomainWithExpirationMinute(Long minutes) {
        return TokenModel.builder()
                .accessToken(AUTHORIZATION_HEADER_BEARER.concat(result.getAccessToken()))
                .expiresIn(minutes)
                .build();
    }
}
