package com.robinfood.app.usecases.orderdetail

import com.robinfood.core.dtos.OrderDetailWithFlagsDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the orders detailed data
 */
interface IGetOrdersDetailUseCase {

    /**
     * Retrieves the orders detail based on orders ids given
     * [token] the authentication token to be used
     * [orderIds] the orders identifiers
     *
     * @return the orders detailed info
     */
    suspend fun invoke(
        token: String,
        orderIds: List<Long>,
        countryId: Long,
        flowId: Long,
        storeId: Long,
        platformId: Long?
    ): Result<OrderDetailWithFlagsDTO>
}