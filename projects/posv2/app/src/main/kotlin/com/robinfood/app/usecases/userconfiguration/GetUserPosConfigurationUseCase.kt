package com.robinfood.app.usecases.userconfiguration

import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.userconfiguration.IUserConfigurationRepository
import org.springframework.stereotype.Service

/**
 * Implementation of IPosStoreUseCase
 */
@Service
class GetUserPosConfigurationUseCase(
    private val posStoreRepository: IUserConfigurationRepository
) : IGetUserPosConfigurationUseCase {
    /**
     * Sends a request to get Pos by Store
     * @param token the authentication token
     * @param userId user Pos Id
     * @return the response Pos Users
     */
    override suspend fun invoke(
        token: String,
        userId: Long
    ): Result<UserConfigurationResponseDTO> {
        return posStoreRepository.getUserPosConfiguration(
            token,
            userId
        )
    }
}
