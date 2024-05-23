package co.com.robinfood.queue.network;

import co.com.robinfood.queue.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        value = "feignBillGenerator",
        url = "${feign.client.url.orderBillNumberGeneratorBc}",
        configuration = FeignConfig.class
)
public interface OrderBillingAPI {

    @PostMapping(value = "v1/send-electronic-bill", consumes = "application/json")
    ResponseEntity<Object> saveOrdersBilling(
            @RequestHeader("Authorization") String token,
            @RequestBody Object transactionRequestDTO
    );

}
