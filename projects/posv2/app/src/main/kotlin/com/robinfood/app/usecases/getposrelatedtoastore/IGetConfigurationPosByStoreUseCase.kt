package com.robinfood.app.usecases.getposrelatedtoastore

import com.robinfood.core.dtos.posrelatedtoastore.StorePosDTO

/**
 * Use case List Configurations Pos by Store
 */
interface IGetConfigurationPosByStoreUseCase {

    /**
     * Sends a request to get List Configurations Pos by Store
     * @param storeId id of store
     * @param token token of authorization
     * @return List Configurations Pos by Store
     */
    suspend fun invoke(
        storeId: Long,
        token: String
    ): List<StorePosDTO>
}