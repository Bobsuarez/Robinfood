package com.robinfood.core.models.retrofit;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_BEARER;

import com.robinfood.core.models.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenResponse {

    private int code;

    private String messages;

    private ResultResponse result;

    private String status;

    public Token toDomainWithExpirationMinute(Long minutes) {
        return Token.builder()
            .accessToken(AUTHORIZATION_HEADER_BEARER.concat(result.getAccessToken()))
            .expiresIn(minutes)
            .build();
    }

}
