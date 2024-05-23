package com.robinfood.localserver.electronicbillresponse.configs.network.feignclients;

import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingRequestEntity;
import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingResponseEntity;
import com.robinfood.localserver.commons.entities.http.ApiResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static com.robinfood.localserver.commons.constants.GlobalConstants.AUTHORIZATION_HEADER_KEY;

@FeignClient(value = "feignStoreOrBilling", url = "${feign.client.url.localserver_ch}")
public interface StoreOrApi {

    /**
     * Connects to an endpoint in billing business capability to create an order electronic billing response
     * @param orderStatusRequest Request body with order electronic billing response data
     * @return the order electronic billing response creation result
     */
    @PostMapping("v1/billing/electronic-billing")
    ApiResponseEntity<ElectronicBillingResponseEntity> createElectronicBilling(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @RequestBody ElectronicBillingRequestEntity orderStatusRequest
    );
}
