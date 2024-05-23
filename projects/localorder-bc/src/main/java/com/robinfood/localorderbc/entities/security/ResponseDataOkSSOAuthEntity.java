package com.robinfood.localorderbc.entities.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseDataOkSSOAuthEntity {

    private String accessToken;

    private Long authExpiration;

}
