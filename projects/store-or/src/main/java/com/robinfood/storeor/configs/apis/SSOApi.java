package com.robinfood.storeor.configs.apis;

import com.robinfood.storeor.configs.FeignClientConfiguration;
import com.robinfood.storeor.entities.token.TokenRequest;
import com.robinfood.storeor.entities.token.TokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "feignsso",
        url = "${feign.url.sso}",
        configuration = FeignClientConfiguration.class)
public interface SSOApi {

    /**
     * Generate SSO Token
     *
     * @param tokenRequest --> Request body to SSO Token service
     * @return --> TokenResponse: Token generated
     */
    @PostMapping(value = "v1/services")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    TokenResponse get(@RequestBody TokenRequest tokenRequest);

}
