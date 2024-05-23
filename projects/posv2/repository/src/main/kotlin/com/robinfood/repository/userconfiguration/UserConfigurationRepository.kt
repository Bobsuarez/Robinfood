package com.robinfood.repository.userconfiguration

import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.userconfiguration.toUserConfigurationResponseDTO
import com.robinfood.network.api.StoreOrAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of IUserConfigurationRepository
 */
@Repository
class UserConfigurationRepository(
    private val storeOrAPI: StoreOrAPI,
    private val dispatchers: DispatcherProvider
) : IUserConfigurationRepository {

    override suspend fun getUserPosConfiguration(
        token: String,
        userId: Long
    ): Result<UserConfigurationResponseDTO> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    storeOrAPI.getUserPosConfiguration(
                        token,
                        userId
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val userConfigurationEntity = result.data.data
                    if (userConfigurationEntity == null) {
                        Result.Error(
                            OrchestratorException("User pos configuration by User is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val userConfigurationResponseDTO = userConfigurationEntity.toUserConfigurationResponseDTO()
                        returnResult(userConfigurationResponseDTO)
                    }
                }
                is Result.Error -> result
            }
        }
    }

    private fun returnResult(
            userConfigurationResponseDTO: UserConfigurationResponseDTO?
    ) : Result<UserConfigurationResponseDTO> {
        return if (userConfigurationResponseDTO == null) {
            Result.Error(
                    OrchestratorException("Error converting user configuration is null"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            )
        } else {
            Result.Success(userConfigurationResponseDTO)
        }
    }
}