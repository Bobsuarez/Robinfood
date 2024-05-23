package com.robinfood.localorderbc.entities.token;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenRequest {

    private String authKey;

    private String authSecret;

    private String issuer;

    public static TokenRequest toThis(String authKey, String authSecret, String issuer) {
        return TokenRequest.builder()
            .authKey(authKey)
            .authSecret(authSecret)
            .issuer(issuer)
            .build();
    }

}
