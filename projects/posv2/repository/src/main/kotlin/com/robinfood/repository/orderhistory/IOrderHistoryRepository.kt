package com.robinfood.repository.orderhistory

import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.orderhistory.OrderHistoryRequestDTO
import com.robinfood.core.enums.Result

/**
 * Repository for order history related data
 */
interface IOrderHistoryRepository {

    suspend fun getOrderHistory(
            token: String,
            orderHistoryRequestDTO: OrderHistoryRequestDTO
    ): Result<HistoryDTO>
}