package com.robinfood.app.usecases.getstoreconfigurations

import com.robinfood.core.dtos.store.StoreConfigurationsDTO

/**
 * Use case Pos Resolution
 */
interface IGetStoreConfigurationUseCase {

    /**
     * Sends a request to get configurations store
     * @param token the authentication token
     * @param storeId id the store
     * @return Configurations Store
     */
    suspend fun invoke(
        storeId: Long,
        token: String
    ): StoreConfigurationsDTO
}