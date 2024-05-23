package com.robinfood.changestatusor.models.retrofit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenRequest {

    @JsonProperty("auth_key")
    private String authKey;

    @JsonProperty("auth_secret")
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
