package com.robinfood.storeor.configs.apis;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import com.robinfood.storeor.entities.TreasureDepartmentsEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingRequestEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

@FeignClient(value = "feignBilling", url = "${feign.client.url.billingBc}")
public interface BillingBcApi {

    /**
     * Get the information of billing-configuration the store by the id
     *
     * @param storeId Identifier of the store
     * @param authorization
     * @return API response with the billing configuration information of a store
     */
    @GetMapping("v1/store/{id}/billing-configuration")
    APIResponseEntity<TreasureDepartmentsEntity> getBillingConfiguration(
        @PathVariable("id") Long storeId,
        @RequestHeader(value = "Authorization") String authorization
    );

    /**
     * Connects to an endpoint in billing business capability to create an order electronic billing response
     * @param token              the authorization token
     * @param orderStatusRequest Request body with order electronic billing response data
     * @return the order electronic billing response creation result
     */
    @PostMapping("v1/billing/electronic-billing")
    APIResponseEntity<ElectronicBillingResponseEntity> createElectronicBilling(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @RequestBody ElectronicBillingRequestEntity orderStatusRequest
    );

    @GetMapping("v1/billing/electronic-billing")
    APIResponseEntity<List<ElectronicBillingEntity>> getElectronicBillByOrdersId(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @RequestParam(
                    value = "orderIds"
            ) List<Long> orderIds
    );

    /**
     * Get the command configuration of the store by the id
     *
     * @param storeId Identifier of the store
     * @return API Response Entity with command configuration of store
     */
    @GetMapping("v1/commands/{id}")
    APIResponseEntity<List<CommandConfigurationEntity>> getCommandConfigurationByStoreId(
            @PathVariable("id") Long storeId,
            @RequestHeader(value = "Authorization") String authorization
    );
}
