package com.robinfood.localprinterbc.feings;

import com.robinfood.localprinterbc.entities.APIResponseEntity;
import com.robinfood.localprinterbc.entities.PrintingTemplateEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.FIND_TEMPLATE_BY_STORE_ID;

@FeignClient(
        value = "feingTemplate",
        url = "${feign.client.url.orderorlocalservertemplateURL}"
)
public interface ConfigurationOrderOrLocalServerAPI {

    @GetMapping(value = FIND_TEMPLATE_BY_STORE_ID, consumes = "application/json")
    APIResponseEntity<PrintingTemplateEntity> getTemplatesByStore(
            @RequestHeader("Authorization") String token,
            @RequestParam("storeId") Long storeId
    );

}
