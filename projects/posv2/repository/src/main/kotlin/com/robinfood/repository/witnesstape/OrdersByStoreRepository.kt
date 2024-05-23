package com.robinfood.repository.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.witnesstape.toStoreOrdersDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class OrdersByStoreRepository(
    private val orderCreationQueriesAPI: OrderCreationQueriesAPI,
    private val dispatchers: DispatcherProvider
) : IOrdersByStoreRepository {

    override suspend fun getOrdersByStore(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): Result<List<StoreOrdersDTO>> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    orderCreationQueriesAPI.getOrdersByStore(
                        token,
                        storeOrderRequestDTO.timeZone,
                        storeOrderRequestDTO.storeId,
                        storeOrderRequestDTO.localDateStart,
                        storeOrderRequestDTO.localDateEnd
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val storeOrdersEntities = result.data.data
                    if (storeOrdersEntities == null) {
                        Result.Error(
                            OrchestratorException("Orders by Store response is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val orderGroupPaymentsDTOs =
                            storeOrdersEntities.mapNotNull { storeOrdersEntity ->
                                storeOrdersEntity.toStoreOrdersDTO()
                            }
                        Result.Success(orderGroupPaymentsDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}