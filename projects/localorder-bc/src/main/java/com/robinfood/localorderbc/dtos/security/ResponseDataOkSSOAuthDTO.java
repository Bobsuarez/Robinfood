package com.robinfood.localorderbc.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ResponseDataOkSSOAuthDTO {

    private String accessToken;

    private Long authExpiration;

}
