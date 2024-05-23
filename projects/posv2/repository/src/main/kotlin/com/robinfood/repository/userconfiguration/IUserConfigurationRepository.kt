package com.robinfood.repository.userconfiguration

import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result

/**
 * Repository for user pos configuration related data
 */
interface IUserConfigurationRepository {

    /**
     * Retrieves the user pos configuration by user
     * [token] the authentication token to be used
     * [userId] the user identification to get pos
     * @return the active pos for the store
     */
    suspend fun getUserPosConfiguration(
            token: String,
            userId: Long
    ): Result<UserConfigurationResponseDTO>

}