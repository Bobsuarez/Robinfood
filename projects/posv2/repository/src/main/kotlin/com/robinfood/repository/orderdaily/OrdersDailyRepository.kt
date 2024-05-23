package com.robinfood.repository.orderdaily

import com.robinfood.core.dtos.OrderDailyDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.toOrderDailyDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class OrdersDailyRepository (
        private val orderCreationQueriesAPI: OrderCreationQueriesAPI
    ): IOrdersDailyRepository{

    override suspend fun getAllOrdersDaily(token: String,
                                           timeZone: String,
                                           storeId: Long): Result<List<OrderDailyDTO>> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    orderCreationQueriesAPI.getOrdersDaily(
                        token,
                        timeZone,
                        storeId
                    ).callServices()
                }
            )
            when(result) {
                is Result.Success -> {
                    val orderDailyEntities = result.data.data
                    if (orderDailyEntities == null) {
                        Result.Error(OrchestratorException("Orders daily array is null"), HttpStatus.INTERNAL_SERVER_ERROR)
                    } else {
                        val orderPayableDTO = orderDailyEntities.mapNotNull { orderDailyEntities -> orderDailyEntities.toOrderDailyDTO() }
                        if (orderPayableDTO.isEmpty()) {
                            Result.Error(OrchestratorException("Orders daily array is empty"), HttpStatus.NOT_FOUND)
                        } else {
                            Result.Success(orderPayableDTO)
                        }
                    }
                }
                is Result.Error -> result
            }
        }
    }
}