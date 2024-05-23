package com.robinfood.repository.store

import com.robinfood.core.dtos.store.StoreDeliveryPlatformDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.store.toDeliveryPlatformListDTO
import com.robinfood.network.api.IntegrationsBackofficeAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of IStoreRepository
 */
@Repository
class StoreRepository(
        private val integrationsBackofficeAPI: IntegrationsBackofficeAPI,
        private val dispatchers: DispatcherProvider
) : IStoreRepository {

    /**
     * Retrieves the store's delivery platforms
     * [page] page
     * [size] the number of delivery platforms per page
     * [storeId] the store identification to get menu
     * [token] the authentication token to be used
     * @return the active delivery platforms for the store
     */
    override suspend fun getStoreDeliveryPlatforms(
            page: Int?,
            size: Int?,
            storeId: Long,
            token: String
    ): Result<List<StoreDeliveryPlatformDTO>> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                    call = {
                        integrationsBackofficeAPI.getStoreDeliveryPlatforms(storeId, page, size, token).callServices()
                    }
            )
            when (result) {
                is Result.Success -> {
                    val deliveryPlatformsResponseEntity = result.data.data
                    if (deliveryPlatformsResponseEntity == null) {
                        Result.Error(
                                OrchestratorException("Delivery Platforms response is null"),
                                HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val deliverPlatformsDTOs = deliveryPlatformsResponseEntity.toDeliveryPlatformListDTO()
                        Result.Success(deliverPlatformsDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}