package com.robinfood.storeor.repositories.resolutionsrepository;

import com.robinfood.storeor.configs.apis.OrdersPosResolutionsApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.SearchResolutionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class ResolutionsOrderPosRepository implements IResolutionsOrderPosRepository {

    private final OrdersPosResolutionsApi ordersPosResolutionsApi;

    public ResolutionsOrderPosRepository(OrdersPosResolutionsApi ordersPosResolutionsApi) {
        this.ordersPosResolutionsApi = ordersPosResolutionsApi;
    }

    @Override
    public APIResponseEntity<Object> createStoreResolutions(
            String token, StoreResolutionsEntity storeResolutionsEntity) {

        return ordersPosResolutionsApi.createStoreResolutions(storeResolutionsEntity, token);
    }

    @Override
    public APIResponseEntity<Object> updateStoreResolutions(
            ResolutionEntity resolutionEntity,
            Long resolutionId,
            String token) {

        return ordersPosResolutionsApi.updateStoreResolutions(resolutionEntity, resolutionId, token);
    }

    @Override
    public APIResponseEntity<Object> activateOrDeactivate(
            ActivateOrDeactivateEntity activateOrDeactivateEntity,
            Long resolutionId,
            String token) {

        return ordersPosResolutionsApi.activateOrDeactivate(activateOrDeactivateEntity, resolutionId, token);
    }

    @Override
    public APIResponseEntity<DataResolutionEntity> findAllResolutions(
            SearchResolutionEntity searchResolutionEntity, String token) {

        return ordersPosResolutionsApi.findAllResolutions(
                searchResolutionEntity.getOrderByEndDateResolution(),
                searchResolutionEntity.getPage(),
                searchResolutionEntity.getSize(),
                searchResolutionEntity.getStatus(),
                searchResolutionEntity.getValueCustomFilter(),
                searchResolutionEntity.getWithPos(),
                searchResolutionEntity.getResolutionId(),
                token
        );

    }
}
