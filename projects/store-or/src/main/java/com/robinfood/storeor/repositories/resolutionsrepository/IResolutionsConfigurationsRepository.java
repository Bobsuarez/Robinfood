package com.robinfood.storeor.repositories.resolutionsrepository;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.response.ResponseResolutionsWithPosEntity;

import java.util.List;

public interface IResolutionsConfigurationsRepository {

    APIResponseEntity<List<ResponseResolutionsWithPosEntity>> createStoreResolutions(
            List<ResolutionEntity> resolutionEntityList,
            String token);

    APIResponseEntity<Object> updateStoreResolutions(
            ResolutionEntity resolutionEntity,
            Long resolutionId,
            String token);

    APIResponseEntity<Object> activateOrDeactivate(
            ActivateOrDeactivateEntity activateOrDeactivateEntity,
            Long resolutionId,
            String token);
}
