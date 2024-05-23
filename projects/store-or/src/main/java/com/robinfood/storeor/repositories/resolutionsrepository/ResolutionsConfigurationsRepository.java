package com.robinfood.storeor.repositories.resolutionsrepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.response.ResponseResolutionsWithPosEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ResolutionsConfigurationsRepository implements IResolutionsConfigurationsRepository {

    private final ConfigurationsBcApi configurationsBcApi;

    public ResolutionsConfigurationsRepository(ConfigurationsBcApi configurationsBcApi) {
        this.configurationsBcApi = configurationsBcApi;
    }

    @Override
    public APIResponseEntity<List<ResponseResolutionsWithPosEntity>> createStoreResolutions(
            List<ResolutionEntity> resolutionEntityList,
            String token) {

        return configurationsBcApi.createStoreResolutions(resolutionEntityList, token);
    }

    @Override
    public APIResponseEntity<Object> updateStoreResolutions(
            ResolutionEntity resolutionEntity,
            Long resolutionId,
            String token) {

        return configurationsBcApi.updateStoreResolution(resolutionEntity, resolutionId, token);
    }

    @Override
    public APIResponseEntity<Object> activateOrDeactivate(
            ActivateOrDeactivateEntity activateOrDeactivateEntity,
            Long resolutionId,
            String token) {

        return configurationsBcApi.activateOrDeactivate(activateOrDeactivateEntity, resolutionId, token);
    }
}
