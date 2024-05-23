package com.robinfood.storeor.repositories.configurationposbystorerepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class StorePosRepository implements IStorePosRepository {

    private final ConfigurationsBcApi configurationsBcApi;

    public StorePosRepository(ConfigurationsBcApi configurationsBcApi) {
        this.configurationsBcApi = configurationsBcApi;
    }

    @Override
    public APIResponseEntity<List<StorePosEntity>> getConfigurationPosByStore(Long storeId, String token) {
        return configurationsBcApi.getConfigurationPosByStore(token, storeId);
    }
}
