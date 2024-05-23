package com.robinfood.repository.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO
import com.robinfood.core.enums.Result

/**
 * Repository for get orders by store related data
 */
interface IOrdersByStoreRepository {

    /**
     * Retrieves the orders by store
     * [storeOrderRequestDTO] properties request
     * [token] the authentication token to be used
     * @return the orders by store
     */
    suspend fun getOrdersByStore(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): Result<List<StoreOrdersDTO>>
}