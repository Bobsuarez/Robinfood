package com.robinfood.storeor.usecase.getbillingconfigurationbystore;

import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;

public interface IGetBillingConfigurationByStoreUseCase {

    /**
     * Use case for getting information by store from the billing-bc configuration
     *
     * @param storeId Identifier of the store
     * @return TreasureDepartmentsDTO configuration
     */
    TreasureDepartmentsDTO invoke(Long storeId, String token);
}
