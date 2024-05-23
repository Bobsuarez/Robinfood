package com.robinfood.localorderbc.dtos.security;

import com.robinfood.localorderbc.entities.token.TokenModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.robinfood.localorderbc.configs.constants.APIConstants.AUTHORIZATION_HEADER_BEARER;

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
