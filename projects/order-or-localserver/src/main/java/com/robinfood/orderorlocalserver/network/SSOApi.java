package com.robinfood.orderorlocalserver.network;

import com.robinfood.orderorlocalserver.configs.feign.FeignConfig;
import com.robinfood.orderorlocalserver.entities.token.TokenRequestEntity;
import com.robinfood.orderorlocalserver.entities.token.TokenResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.robinfood.orderorlocalserver.constants.ApiConstants.API_GET_SSO_TOKEN;

@FeignClient(value = "feignSsoApi", url = "${feign.client.url.ssoUrl}", configuration = FeignConfig.class)
public interface SSOApi {

    @PostMapping(API_GET_SSO_TOKEN)
    ResponseEntity<TokenResponseEntity> get(@RequestBody TokenRequestEntity tokenRequestEntity);
}
