package com.robinfood.storeor.repositories.configurationposbystorerepository;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;

import java.util.List;

public interface IStorePosRepository {

    APIResponseEntity<List<StorePosEntity>>getConfigurationPosByStore(Long storeId, String token);
}
