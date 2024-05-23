package com.robinfood.repository.orderdetail

import com.robinfood.core.dtos.OrderDetailDTO
import com.robinfood.core.enums.Result

/**
 * Repository for order detail related data
 */
interface IOrderDetailRepository {

    /**
     * Retrieves the orders detail based on orders identifiers given
     * [token] the authentication token to be used
     * [countryId] the country id
     * [flowId] the flow id
     * [orderIds] the orders identifiers
     * @return the orders detailed info
     */
    suspend fun getOrdersDetail(
        token: String,
        countryId: Long,
        flowId: Long,
        orderIds: List<Long>
    ): Result<List<OrderDetailDTO>>
}