package com.robinfood.localorderbc.dtos.security;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenPayloadSSOLoginScopeDTO {

    private String refreshToken;

    private Long refreshExpiresIn;

}
