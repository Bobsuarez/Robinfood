package com.robinfood.paymentmethodsbc.security.sso;

import com.robinfood.paymentmethodsbc.configs.FeignConfig;
import com.robinfood.paymentmethodsbc.dto.sso.SSOGenericResponseDTO;
import com.robinfood.paymentmethodsbc.dto.sso.SSOServiceTokenDTO;
import com.robinfood.paymentmethodsbc.dto.sso.SSOServiceTokenRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "sso-http-client",
    configuration = FeignConfig.class,
    url = "${sso.url.base}",
    primary = false
)
@Component
public interface SSOClient {
    @PostMapping(
        path = "${sso.url.services}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    SSOGenericResponseDTO<SSOServiceTokenDTO> getServiceToken(
        @RequestBody SSOServiceTokenRequestDTO body
    );
}
