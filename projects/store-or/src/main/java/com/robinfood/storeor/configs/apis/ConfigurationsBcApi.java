package com.robinfood.storeor.configs.apis;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.StoreEntity;
import com.robinfood.storeor.entities.UserStoreEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;
import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import com.robinfood.storeor.entities.response.ResponseResolutionsWithPosEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.ACTIVATE_OR_DEACTIVATE_POS_CONFIGURATIONS;
import static com.robinfood.storeor.configs.constants.APIConstants.AUTHORIZATION_HEADER_KEY;
import static com.robinfood.storeor.configs.constants.APIConstants.CREATE_POS_CONFIGURATIONS;
import static com.robinfood.storeor.configs.constants.APIConstants.CREATE_RESOLUTIONS_CONFIGURATIONS;
import static com.robinfood.storeor.configs.constants.APIConstants.GET_LIST_POS;
import static com.robinfood.storeor.configs.constants.APIConstants.TURN_OR_OFF_RESOLUTIONS_CONFIGURATIONS;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATE_POS_CONFIGURATIONS;
import static com.robinfood.storeor.configs.constants.APIConstants.UPDATE_RESOLUTIONS_CONFIGURATIONS;

@Component
@FeignClient(value = "feignStore",
        url = "${feign.client.url.configurationsBc}")
public interface ConfigurationsBcApi {

    /**
     * Get the information of the store by the id
     *
     * @param storeId Identifier of the store
     * @return API Response Entity detail store
     */
    @GetMapping("v1/stores/{id}")
    APIResponseEntity<StoreEntity> getStoreById(
            @PathVariable("id") Long storeId,
            @RequestHeader(value = "Authorization") String authorization
    );

    /**
     * Get the information of the store list
     *
     * @param name             name of the store
     * @param companyCountryId company country associate to store
     * @param page             number of page
     * @param size             size of store list
     * @param sort             sort store list
     * @return API Response Entity detail store list
     */
    @GetMapping("v1/stores")
    APIResponseEntity<RestResponsePage<StoreEntity>> getStoreList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "company_country_id", required = false) Long companyCountryId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size, Sort sort,
            @RequestHeader(value = "Authorization") String authorization
    );


    @GetMapping(value = "v1/user-stores/users/{userId}/stores")
    APIResponseEntity<UserStoreEntity> findStoreByUserId(
            @PathVariable(value = "userId", required = true) Long userId,
            @RequestHeader(value = "Authorization") String authorization
    );

    /**
     * Connects to an endpoint in configuration-bc capability to get list of pos by store
     *
     * @param token   the authorization token
     * @param storeId id of store by response data
     * @return the configuration-bc capability to get list of pos by store result
     */
    @GetMapping("v1/pos/stores/{storeId}")
    APIResponseEntity<List<StorePosEntity>> getConfigurationPosByStore(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @PathVariable(value = "storeId") Long storeId
    );

    /**
     * Connects to an endpoint in configuration-bc capability to create resolutions of pos
     *
     * @param token                the authorization token
     * @param resolutionEntityList Request body list of resolutions by store
     * @return the resolutions response creation result
     */
    @PostMapping(CREATE_RESOLUTIONS_CONFIGURATIONS)
    APIResponseEntity<List<ResponseResolutionsWithPosEntity>> createStoreResolutions(
            @RequestBody List<ResolutionEntity> resolutionEntityList,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in configuration-bc capability to update resolutions of pos
     *
     * @param resolutionEntity Request body resolution to be updated
     * @param resolutionId     Request id of resolution to be updated
     * @param token            the authorization token
     * @return Updated resolution response
     */
    @PutMapping(UPDATE_RESOLUTIONS_CONFIGURATIONS)
    APIResponseEntity<Object> updateStoreResolution(
            @RequestBody ResolutionEntity resolutionEntity,
            @PathVariable(value = "resolutionId") Long resolutionId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in configuration-bc capability to update resolutions of pos
     *
     * @param activateOrDeactivateEntity Request active or deactivate resolutions by pos
     * @param resolutionId               Request id of resolution to be updated
     * @param token                      the authorization token
     * @return Updated resolution response
     */
    @PatchMapping(TURN_OR_OFF_RESOLUTIONS_CONFIGURATIONS)
    APIResponseEntity<Object> activateOrDeactivate(
            @RequestBody ActivateOrDeactivateEntity activateOrDeactivateEntity,
            @PathVariable(value = "id") Long resolutionId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in configuration-bc capability to create pos of stores
     *
     * @param token                the authorization token
     * @param storeCreatePosEntity Request body list of pos by store
     * @return the list pos response creation result
     */
    @PostMapping(CREATE_POS_CONFIGURATIONS)
    APIResponseEntity<StoreCreatePosEntity> createPos(
            @RequestBody StoreCreatePosEntity storeCreatePosEntity,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );


    /**
     * Connects to an endpoint in configuration-bc capability to update pos
     *
     * @param posEntity Request of the entity for api
     * @param posId     Request id of pos to be updated
     * @param token     the authorization token
     * @return Updated pos response
     */
    @PutMapping(UPDATE_POS_CONFIGURATIONS)
    APIResponseEntity<PosEntity> updatePos(
            @RequestBody PosDTO posEntity,
            @PathVariable(value = "id") Long posId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in configuration-bc capability to update pos
     *
     * @param activateOrDeactivatePosEntity Request active or deactivate pos
     * @param posId                         Request id of pos to be updated
     * @param token                         the authorization token
     * @return Updated pos response
     */
    @PatchMapping(ACTIVATE_OR_DEACTIVATE_POS_CONFIGURATIONS)
    APIResponseEntity<Object> activateOrDeactivatePos(
            @RequestBody ActivateOrDeactivatePosEntity activateOrDeactivatePosEntity,
            @PathVariable(value = "id") Long posId,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in configuration-bc capability to get list of pos
     *
     * @param token   the authorization token
     * @param size    number of records per query
     * @param page    Page number
     * @param posName Pos name
     * @param status  pos status
     * @param storeId store id
     * @param sort    sorting by id and posName
     * @return pos list with its resolution
     */
    @GetMapping(GET_LIST_POS)
    APIResponseEntity<RestResponsePage<PosListResponseEntity>> getListPos(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "posName", required = false) String posName,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "status", required = false) Long status,
            @RequestParam(value = "storeId", required = false) Long storeId,
            Sort sort,
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token
    );
}
