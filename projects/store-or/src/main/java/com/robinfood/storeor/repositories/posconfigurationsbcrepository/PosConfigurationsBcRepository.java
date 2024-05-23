package com.robinfood.storeor.repositories.posconfigurationsbcrepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.configs.apis.OrdersPosResolutionsApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;


@Repository
@Slf4j
public class PosConfigurationsBcRepository implements IPosConfigurationsBcRepository {

    private final ConfigurationsBcApi configurationsBcApi;

    private final OrdersPosResolutionsApi ordersPosResolutionsApi;

    public PosConfigurationsBcRepository(
            ConfigurationsBcApi configurationsBcApi,
            OrdersPosResolutionsApi ordersPosResolutionsApi
    ) {
        this.configurationsBcApi = configurationsBcApi;
        this.ordersPosResolutionsApi = ordersPosResolutionsApi;
    }

    @Override
    public APIResponseEntity<StoreCreatePosEntity> createPosConfiguration(
            String token,
            StoreCreatePosEntity storeCreatePosEntity) {
        APIResponseEntity<StoreCreatePosEntity> response = configurationsBcApi.createPos(storeCreatePosEntity, token);

        if (response.getData()!=null && !response.getData().getPos().isEmpty()) {

            final Long posId = response.getData().getPos().get(0).getId();
            final Long resolutionId;

            if(Objects.nonNull(storeCreatePosEntity.getPos().get(0).getResolutionsIds().getId())){
                resolutionId = storeCreatePosEntity.getPos().get(0).getResolutionsIds().getId();
                ordersPosResolutionsApi.updateResolutionWithPos(resolutionId, posId, token);
            }
        }
        return response;
    }

    @Override
    public APIResponseEntity<PosEntity> updatePosConfigurationBc(PosDTO posEntity, Long posId, String token) {

        APIResponseEntity<PosEntity> response = configurationsBcApi.updatePos(posEntity, posId, token);

        if (Objects.nonNull(posEntity.getResolutionsIds())) {
            ordersPosResolutionsApi.updateResolutionWithPos(posEntity.getResolutionsIds().getId(), posId,
                    token);
        }
        return response;
    }

    @Override
    public APIResponseEntity<Object> activateOrDeactivatePosConfigurations(
            ActivateOrDeactivatePosEntity activateOrDeactivatePosEntity,
            Long posId,
            String token
    ) {

        return configurationsBcApi.activateOrDeactivatePos(activateOrDeactivatePosEntity, posId, token);
    }
}
