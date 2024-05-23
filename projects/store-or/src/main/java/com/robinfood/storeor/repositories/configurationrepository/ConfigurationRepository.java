package com.robinfood.storeor.repositories.configurationrepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.StoreEntity;
import com.robinfood.storeor.entities.UserStoreEntity;
import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IConfigurationRepository
 */
@Repository
@Slf4j
public class ConfigurationRepository implements IConfigurationRepository {

    private final ConfigurationsBcApi configurationsBcApi;

    public ConfigurationRepository(ConfigurationsBcApi configurationsBcApi) {
        this.configurationsBcApi = configurationsBcApi;
    }

    public APIResponseEntity<StoreEntity> getStore(Long storeId, String token) {

        log.info("ConfigurationRepository execute getStore() Get Configuration by store: {}", storeId);

        return configurationsBcApi.getStoreById(storeId, token);
    }

    public APIResponseEntity<RestResponsePage<StoreEntity>> getStore(
            String name,
            Long companyCountryId, Integer page,
            Integer size, Sort sort, String accessToken
    ) {
        return configurationsBcApi.getStoreList(name, companyCountryId, page, size, sort, accessToken);
    }

    @Override
    public APIResponseEntity<UserStoreEntity> findStoreByUserId(Long userId, String token) {
        return configurationsBcApi.findStoreByUserId(userId, token);
    }

    @Override
    public APIResponseEntity<RestResponsePage<PosListResponseEntity>> getListPos(
            Integer page, String posName, Integer size,
            Long status, Long storeId, Sort sort, String accessToken) {
        return configurationsBcApi.getListPos(page, posName, size, status, storeId, sort, accessToken);
    }
}
