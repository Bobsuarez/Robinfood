package com.robinfood.app.usecases.getorderhistory

import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.orderhistory.OrderHistoryRequestDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the order history
 */
interface IGetOrderHistoryUseCase {

    suspend fun invoke(
        token: String,
        orderHistoryRequestDTO: OrderHistoryRequestDTO
    ): Result<HistoryDTO>
}