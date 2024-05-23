package com.robinfood.repository.storeconfigurations

import com.robinfood.core.dtos.store.StoreConfigurationsDTO
import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result

/**
 * Repository for configuration related store
 */
interface IStoreConfigurationRepository {

    /**
     * Retrieves the user pos configuration by user
     * [token] the authentication token to be used
     * [storeId] the store identification to get configurations
     * @return the configurations of store
     */
    suspend fun getStoreConfiguration(
            token: String,
            storeId: Long
    ): Result<StoreConfigurationsDTO>

}