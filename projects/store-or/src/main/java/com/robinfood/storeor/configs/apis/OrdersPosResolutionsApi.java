package com.robinfood.storeor.configs.apis;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import static com.robinfood.storeor.configs.constants.APIConstants.AUTHORIZATION_HEADER_KEY;
import static com.robinfood.storeor.configs.constants.APIConstants.CREATE_RESOLUTIONS_ORDERS;
import static com.robinfood.storeor.configs.constants.APIConstants.TURN_OR_OFF_RESOLUTIONS_ORDERS;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATE_RESOLUTIONS_ORDERS;
import static com.robinfood.storeor.configs.constants.APIConstants.FIND_ALL_RESOLUTIONS;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATE_RESOLUTIONS_WITH_POS;

@Component
@FeignClient(value = "feignOrders",
        url = "${feign.client.url.ordersposresolutionsBc}")
public interface OrdersPosResolutionsApi {

    /**
     * Connects to an endpoint in configuration-bc capability to create resolutions of pos
     *
     * @param storeResolutionsEntity Request body list of resolutions by store*
     * @param token                  the authorization token
     * @return the resolutions response creation result
     */
    @PostMapping(CREATE_RESOLUTIONS_ORDERS)
    APIResponseEntity<Object> createStoreResolutions(
            @RequestBody StoreResolutionsEntity storeResolutionsEntity,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in configuration-bc capability to update resolutions of pos
     *
     * @param resolutionEntity Request body resolutions object by store
     * @param token            the authorization token
     * @return the resolution response creation result
     */
    @PutMapping(UPDATE_RESOLUTIONS_ORDERS)
    APIResponseEntity<Object> updateStoreResolutions(
            @RequestBody ResolutionEntity resolutionEntity,
            @PathVariable("resolutionId") Long resolutionId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in orders capability to update resolutions of pos
     *
     * @param activateOrDeactivateEntity Request active or deactivate resolutions by pos
     * @param resolutionId               Request id of resolution to be updated
     * @param token                      the authorization token
     * @return Updated resolution response
     */
    @PatchMapping(TURN_OR_OFF_RESOLUTIONS_ORDERS)
    APIResponseEntity<Object> activateOrDeactivate(
            @RequestBody ActivateOrDeactivateEntity activateOrDeactivateEntity,
            @PathVariable(value = "resolutionId") Long resolutionId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    @GetMapping(FIND_ALL_RESOLUTIONS)
    APIResponseEntity<DataResolutionEntity> findAllResolutions(
            @RequestParam(value = "orderByEndDateResolution") String orderByEndDateResolution,
            @RequestParam(value = "page") Integer  page,
            @RequestParam(value = "size") Integer  size,
            @RequestParam(value = "status") Boolean  status,
            @RequestParam(value = "valueCustomFilter") String valueCustomFilter,
            @RequestParam(value = "withPos") Boolean  withPos,
            @RequestParam(value = "resolutionId") Long  resolutionId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    @PutMapping(UPDATE_RESOLUTIONS_WITH_POS)
    APIResponseEntity<Object> updateResolutionWithPos(
            @PathVariable("id") Long id,
            @PathVariable("posId") Long posId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );
}
