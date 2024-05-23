package com.robinfood.entities.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenRequestEntity {

    @JsonProperty("auth_key")
    private String authKey;

    @JsonProperty("auth_secret")
    private String authSecret;

    private String issuer;
}
