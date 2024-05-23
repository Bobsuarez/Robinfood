package com.robinfood.repository.configuration.store;

import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Get store configuration by store identifier repository
 */
public interface IStoreRepository {

    /**
     * Get store configuration by store identifier
     *
     * @param storeId The store identifier
     * @param token Token auth service
     * @return Result store
     */
    Result<StoreDTO> getStore(Long storeId, String token);

    /**
     * Get store configuration by stores
     *
     * @param token token auth service
     * @return Result stores
     */
    Result<List<StoreDTO>> getStores(String token);
}
