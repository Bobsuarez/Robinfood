package com.robinfood.storeor.repositories.posrepository;

import com.robinfood.storeor.configs.apis.ConfigurationPosBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class PosRepository implements IPosRepository{

    private final ConfigurationPosBcApi configurationPosBcApi;

    public PosRepository(
            ConfigurationPosBcApi configurationPosBcApi
    ) {
        this.configurationPosBcApi = configurationPosBcApi;
    }

    @Override
    public APIResponseEntity<PosEntity> getPosConfiguration(String token, Long userId, Long storeId) {
        return configurationPosBcApi.getConfigurationPosByUserIdAndStoreId(token, storeId, userId);
    }
}
