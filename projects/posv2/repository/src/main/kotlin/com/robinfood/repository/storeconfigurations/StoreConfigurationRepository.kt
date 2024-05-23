package com.robinfood.repository.storeconfigurations

import com.robinfood.core.dtos.store.StoreConfigurationsDTO
import com.robinfood.core.dtos.userconfiguration.UserConfigurationResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.store.toStoreConfigurationDTO
import com.robinfood.core.mappers.userconfiguration.toUserConfigurationResponseDTO
import com.robinfood.network.api.StoreOrAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class StoreConfigurationRepository(
    private val storeOrAPI: StoreOrAPI,
    private val dispatchers: DispatcherProvider
): IStoreConfigurationRepository {

    override suspend fun getStoreConfiguration(token: String, storeId: Long): Result<StoreConfigurationsDTO> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    storeOrAPI.getStoreConfiguration(
                        token,
                        storeId
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val storeConfigurationsEntity = result.data.data
                    if (storeConfigurationsEntity == null) {
                        Result.Error(
                            OrchestratorException("User pos configuration by User is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val storeConfigurationsDTO = storeConfigurationsEntity.toStoreConfigurationDTO()
                        returnResult(storeConfigurationsDTO)
                    }
                }
                is Result.Error -> result
            }
        }
    }

    private fun returnResult(
        storeConfigurationsDTO: StoreConfigurationsDTO?
    ) : Result<StoreConfigurationsDTO> {
        return if (storeConfigurationsDTO == null) {
            Result.Error(
                OrchestratorException("Error converting store settings is null"),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        } else {
            Result.Success(storeConfigurationsDTO)
        }
    }
}