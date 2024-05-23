package com.robinfood.repository.posrelatedtoastore

import com.robinfood.core.dtos.posrelatedtoastore.StorePosDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.posrelatedtoastore.toStorePosDTO
import com.robinfood.network.api.StoreOrAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class ConfigurationPosByStoreRepository(
    private val storeOrAPI: StoreOrAPI,
    private val dispatchers: DispatcherProvider
) : IConfigurationPosByStoreRepository {

    override suspend fun getPosRelatedToAStore(
        storeId: Long,
        token: String
    ): Result<List<StorePosDTO>> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    storeOrAPI.getPosByStoreConfiguration(
                        token,
                        storeId
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val storePosEntities = result.data.data
                    if (storePosEntities == null) {
                        Result.Error(
                            OrchestratorException("Pos By Store response is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val storePosDTOs =
                            storePosEntities.mapNotNull { storePosEntity ->
                                storePosEntity.toStorePosDTO()
                            }
                        Result.Success(storePosDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}