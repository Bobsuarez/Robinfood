package com.robinfood.orderorlocalserver.entities.token;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.robinfood.orderorlocalserver.constants.ApiConstants.AUTHORIZATION_HEADER_BEARER;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenResponseEntity {

    private int code;

    private String messages;

    private ResultResponseEntity result;

    private String status;

    public TokenModelEntity toDomainWithExpirationMinute(Long minutes) {
        return TokenModelEntity.builder()
            .accessToken(AUTHORIZATION_HEADER_BEARER.concat(result.getAccessToken()))
            .expiresIn(minutes)
            .build();
    }

}
