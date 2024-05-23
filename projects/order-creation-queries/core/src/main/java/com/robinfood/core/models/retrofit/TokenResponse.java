package com.robinfood.core.models.retrofit;

import com.robinfood.core.models.domain.TokenModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_BEARER;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenResponse {

    private int code;

    private String messages;

    private ResultResponse result;

    private String status;

    public TokenModel toDomainWithExpirationMinute(Long minutes) {
        return TokenModel.builder()
            .accessToken(AUTHORIZATION_HEADER_BEARER.concat(result.getAccessToken()))
            .expiresIn(minutes)
            .build();
    }

}
