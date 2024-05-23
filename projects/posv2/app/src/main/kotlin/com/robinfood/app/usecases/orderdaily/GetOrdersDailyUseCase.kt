package com.robinfood.app.usecases.orderdaily

import com.robinfood.core.dtos.OrderDailyResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.orderdaily.IOrdersDailyRepository

class GetOrdersDailyUseCase(
        private val ordersDailyRepository: IOrdersDailyRepository
) : IGetOrdersDailyUseCase {

    override suspend fun invoke(
            token: String,
            timeZone: String,
            storeId: Long
    ): Result<OrderDailyResponseDTO> {

        return when (val result = ordersDailyRepository.getAllOrdersDaily(token, timeZone, storeId)) {

            is Result.Success -> Result.Success(OrderDailyResponseDTO(result.data))
            is Result.Error -> Result.Error(result.exception, result.httpStatus)
        }
    }
}