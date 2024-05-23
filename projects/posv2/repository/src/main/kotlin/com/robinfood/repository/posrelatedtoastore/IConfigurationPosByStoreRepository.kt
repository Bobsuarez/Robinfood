package com.robinfood.repository.posrelatedtoastore

import com.robinfood.core.dtos.posrelatedtoastore.StorePosDTO
import com.robinfood.core.enums.Result

/**
 * Repository for get relation pos by store related data
 */
interface IConfigurationPosByStoreRepository {

    /**
     * Retrieves the orders by store
     * [storeId] store id request
     * [token] the authentication token to be used
     * @return the relations pos by store
     */
    suspend fun getPosRelatedToAStore(
        storeId: Long,
        token: String
    ): Result<List<StorePosDTO>>
}