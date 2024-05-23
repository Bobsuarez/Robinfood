package com.robinfood.app.usecases.getstoreinfo;

import com.robinfood.core.models.domain.configuration.StoreInformation;

public interface IGetStoreInfoUseCase {
    /**
     * Consult the store info fromn the database
     *
     * @param token   the authorization token
     * @param storeId the storeId of the order
     */
    StoreInformation invoke(String token, Long storeId);
}
