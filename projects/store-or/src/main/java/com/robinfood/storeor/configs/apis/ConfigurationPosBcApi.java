package com.robinfood.storeor.configs.apis;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.robinfood.storeor.configs.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

@FeignClient(value = "feignConfigurationPos", url = "${feign.client.url.configurationPosBc}")
public interface ConfigurationPosBcApi {

    @GetMapping("v1/configuration/pos")
    APIResponseEntity<PosEntity> getConfigurationPosByUserIdAndStoreId(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @RequestParam(value = "storeId") Long  storeId,
            @RequestParam(value = "userId") Long  userId
    );
}
