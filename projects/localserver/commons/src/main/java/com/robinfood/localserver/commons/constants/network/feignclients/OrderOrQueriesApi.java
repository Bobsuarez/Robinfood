package com.robinfood.localserver.commons.constants.network.feignclients;

import com.robinfood.localserver.commons.entities.http.ApiResponseOrderOrQueriesEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.robinfood.localserver.commons.constants.GlobalConstants.AUTHORIZATION_HEADER_KEY;

@FeignClient(value = "feignStoreOrBilling", url = "${feign.client.url.orderorqueries}")
public interface OrderOrQueriesApi {

    /**
     * Connects to an endpoint in order-or-queries for get order-detail-print
     * @param ordersIds Request body with order electronic billing response data
     * @return the order electronic billing response creation result
     */
    @GetMapping("orders/detail/print")
    ApiResponseOrderOrQueriesEntity getOrderDetail(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @RequestParam("orderIds") List<Long> ordersIds
    );
}
