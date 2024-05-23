package com.robinfood.localserver.commons.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenSSOLoginScopeDTO {

    private TokenPayloadSSOLoginScopeDTO token;
}
