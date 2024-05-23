package com.robinfood.changestatusor.models.retrofit;

import com.robinfood.changestatusor.models.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.robinfood.changestatusor.constants.APIConstants.AUTHORIZATION_HEADER_BEARER;

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
                .accessToken(AUTHORIZATION_HEADER_BEARER.concat(result.getAccess_token()))
                .expiresIn(minutes)
                .build();
    }
}
