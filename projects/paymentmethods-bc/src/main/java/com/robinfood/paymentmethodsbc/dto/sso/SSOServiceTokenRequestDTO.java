package com.robinfood.paymentmethodsbc.dto.sso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(hidden = true)
public class SSOServiceTokenRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("issuer")
    private String issuer;

    @JsonProperty("auth_key")
    private String authKey;

    @JsonProperty("auth_secret")
    private String authSecret;
}
