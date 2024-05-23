package com.robinfood.storeor.repositories.posconfigurationsbcrepository;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;

public interface IPosConfigurationsBcRepository {

    APIResponseEntity<StoreCreatePosEntity> createPosConfiguration(
            String token,
            StoreCreatePosEntity storeCreatePosEntity
    );

    APIResponseEntity<PosEntity> updatePosConfigurationBc(PosDTO posEntity, Long posId, String token);

    /**
     * activate or deactivate a pos in database configurations-bc
     *
     * @param activateOrDeactivatePosEntity Contains data to activate or deactivate a POS per store
     * @param posId                         id of the pos to activate or deactivate
     * @param token                         security token
     */
    APIResponseEntity<Object> activateOrDeactivatePosConfigurations(
            ActivateOrDeactivatePosEntity activateOrDeactivatePosEntity,
            Long posId,
            String token
    );
}
