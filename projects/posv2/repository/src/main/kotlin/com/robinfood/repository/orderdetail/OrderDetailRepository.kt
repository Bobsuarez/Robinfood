package com.robinfood.repository.orderdetail

import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.toOrderDetailDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of IOrderDetailRepository
 */
@Repository
class OrderDetailRepository (
        private val orderCreationQueriesAPI: OrderCreationQueriesAPI
): IOrderDetailRepository {

    override suspend fun getOrdersDetail(
            token: String,
            countryId: Long,
            flowId: Long,
            orderIds: List<Long>
    ): Result<List<OrderDetailDTO>> {

        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                    call = {
                        orderCreationQueriesAPI.getOrdersDetail(
                                token,
                                countryId,
                                flowId,
                                orderIds
                        ).callServices()
                    }
            )
            when(result) {
                is Result.Success -> {
                    val orderDetailEntities = result.data.data
                    if (orderDetailEntities == null) {
                        Result.Error(OrchestratorException("Orders detail array is null"), HttpStatus.INTERNAL_SERVER_ERROR)
                    } else {
                        val orderDetailDTOs = orderDetailEntities.mapNotNull { orderDetailEntity -> orderDetailEntity.toOrderDetailDTO() }
                        if (orderDetailDTOs.isEmpty()) {
                            Result.Error(OrchestratorException("Orders detail array is empty"), HttpStatus.INTERNAL_SERVER_ERROR)
                        } else {
                            Result.Success(orderDetailDTOs)
                        }
                    }
                }
                is Result.Error -> result
            }
        }
    }
}