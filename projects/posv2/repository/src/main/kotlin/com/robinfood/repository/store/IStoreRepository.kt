package com.robinfood.repository.store

import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import com.robinfood.core.enums.Result

/**
 * Repository for store related data
 */
interface IStoreRepository {

    /**
     * Retrieves the store's delivery platforms
     * [page] page
     * [size] the number of delivery platforms per page
     * [storeId] the store identification to get menu
     * [token] the authentication token to be used
     * @return the active delivery platforms for the store
     */
    suspend fun getStoreDeliveryPlatforms(
            page: Int?,
            size: Int?,
            storeId: Long,
            token: String
    ): Result<List<StoreDeliveryPlatformDTO>>
}