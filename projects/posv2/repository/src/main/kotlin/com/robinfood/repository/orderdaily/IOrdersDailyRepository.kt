package com.robinfood.repository.orderdaily

import com.robinfood.core.dtos.OrderDailyDTO
import com.robinfood.core.enums.Result

interface IOrdersDailyRepository {

    suspend fun getAllOrdersDaily(token: String,
                                  timeZone: String,
                                  storeId: Long
    ): Result<List<OrderDailyDTO>>
}