package com.robinfood.storeor.repositories.resolutionsrepository;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.SearchResolutionEntity;


public interface IResolutionsOrderPosRepository {

    APIResponseEntity<Object> createStoreResolutions(
            String token, StoreResolutionsEntity storeResolutionsEntity);

    APIResponseEntity<Object> updateStoreResolutions(
            ResolutionEntity resolutionEntity,
            Long resolutionId,
            String token);

    APIResponseEntity<Object> activateOrDeactivate(
            ActivateOrDeactivateEntity activateOrDeactivateEntity,
            Long resolutionId,
            String token);

    APIResponseEntity<DataResolutionEntity> findAllResolutions(
            SearchResolutionEntity searchResolutionEntity,
            String token);
}
