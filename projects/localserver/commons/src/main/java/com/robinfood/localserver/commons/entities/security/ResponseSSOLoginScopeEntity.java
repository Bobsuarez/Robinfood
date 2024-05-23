package com.robinfood.localserver.commons.entities.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResponseSSOLoginScopeEntity {

    private String status;

    private String code;

    private ResponseDataOkSSOLoginScopeEntity result;

}
