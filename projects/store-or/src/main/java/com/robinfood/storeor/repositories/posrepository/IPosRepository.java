package com.robinfood.storeor.repositories.posrepository;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;

public interface IPosRepository {

    APIResponseEntity<PosEntity>getPosConfiguration(String token, Long userId, Long storeId);

}
