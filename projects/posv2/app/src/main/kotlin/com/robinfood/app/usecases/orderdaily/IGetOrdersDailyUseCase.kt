package com.robinfood.app.usecases.orderdaily

import com.robinfood.core.dtos.OrderDailyResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves all orders to be invoiced
 */
interface IGetOrdersDailyUseCase {

    suspend fun invoke(
            token: String,
            timeZone: String,
            storeId: Long
    ): Result<OrderDailyResponseDTO>
}