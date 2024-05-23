package com.robinfood.orderorlocalserver.network;

import com.robinfood.orderorlocalserver.configs.feign.FeignConfig;
import com.robinfood.orderorlocalserver.entities.APIResponseEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static com.robinfood.orderorlocalserver.constants.ApiConstants.API_GET_ORDER_DETAIL;

@FeignClient(value = "feignOrderBc", url = "${feign.client.url.orderBcUrl}", configuration = FeignConfig.class)
public interface OrderBcAPI {

    @GetMapping(value = API_GET_ORDER_DETAIL, consumes = "application/json")
    APIResponseEntity<List<OrderDetailEntity>> getOrderDetailPrint(
            @RequestHeader("Authorization") String token,
            @RequestParam("orderIds") List<Long> orderIds,
            @RequestParam("orderUids") List<String> orderUids,
            @RequestParam("orderUuid") List<String> orderUuid
    );
}
