package com.robinfood.localorderbc.configs.apis;

import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.entities.APIResponseEntity;
import com.robinfood.localorderbc.entities.changestate.ChangeStateEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.robinfood.localorderbc.configs.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

@FeignClient(value = "feignChangeState", url = "${feign.client.url.common_integration_layer}")
public interface OrderCreationApi {

    /**
     * Connects to an endpoint in order creation for post states
     * @param changeStateDTO entry contract for change of status
     * @return the Change of status of a successful or failed order
     */
    @PostMapping("v1/orders/change-status")
    APIResponseEntity<ChangeStateEntity> changeState(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @RequestBody ChangeStateDTO changeStateDTO);
}
