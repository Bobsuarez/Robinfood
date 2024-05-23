package com.robinfood.orderorlocalserver.network;

import com.robinfood.orderorlocalserver.configs.feign.FeignConfig;
import com.robinfood.orderorlocalserver.entities.APIResponseEntity;
import com.robinfood.orderorlocalserver.entities.printingtemplate.PrintingTemplateEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import static com.robinfood.orderorlocalserver.constants.ApiConstants.CONFIGURATION_GET_TEMPLATE_V1;

@FeignClient(
        value = "feignOrderBc",
        url = "${feign.client.url.configurationsLocalserverBcUrl}",
        configuration = FeignConfig.class
)
public interface ConfigurationsLocalServerBcAPI {

    @GetMapping(value = CONFIGURATION_GET_TEMPLATE_V1, consumes = "application/json")
    APIResponseEntity<PrintingTemplateEntity> getTemplatesByStore(
            @RequestHeader("Authorization") String token,
            @RequestParam("storeId") Long storeId
    );
}
