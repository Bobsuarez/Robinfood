package com.robinfood.localorderbc.entities.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseDataOkSSOLoginScopeEntity {

    private TokenSSOLoginScopeEntity token;
}
