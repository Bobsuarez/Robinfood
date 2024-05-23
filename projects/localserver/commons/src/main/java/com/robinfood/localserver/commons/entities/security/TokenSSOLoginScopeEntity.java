package com.robinfood.localserver.commons.entities.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenSSOLoginScopeEntity {

    private TokenPayloadSSOLoginScopeEntity token;
}
