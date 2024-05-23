package co.com.robinfood.queue.network;

import co.com.robinfood.queue.feign.FeignConfig;
import co.com.robinfood.queue.persistencia.entity.token.TokenRequestEntity;
import co.com.robinfood.queue.persistencia.entity.token.TokenResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "feignSsoApi",
        url = "${feign.client.url.ssoUrl}",
        configuration = FeignConfig.class
)
public interface SSOTokenApi {

    @PostMapping("v1/services")
    ResponseEntity<TokenResponseEntity> get(@RequestBody TokenRequestEntity tokenRequestEntity);
}

