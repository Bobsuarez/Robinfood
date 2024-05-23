package com.robinfood.app.usecases.userconfiguration

import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case Pos Response
 */
interface IGetUserPosConfigurationUseCase {

    /**
     * Sends a request to get user pos configuration By User
     * @param token the authentication token
     * @param userId userId related the pos
     * @return the pos configurations by User response
     */
    suspend fun invoke(
            token: String,
            userId: Long
    ): Result<UserConfigurationResponseDTO>
}