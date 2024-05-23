package com.robinfood.localserver.commons.dtos.security;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CredentialsAuthenticationDTO {

    private String authorizationToken;

    private String issuer;

    private String scope;
}
