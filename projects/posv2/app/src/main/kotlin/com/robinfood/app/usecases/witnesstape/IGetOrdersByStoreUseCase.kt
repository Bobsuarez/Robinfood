package com.robinfood.app.usecases.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO

/**
 * Use case List Orders by Store
 */
interface IGetOrdersByStoreUseCase {

    /**
     * Sends a request to get report pdf
     * @param storeOrderRequestDTO request store orders
     * @param token token of authorization
     * @return the List Orders by Store
     */
    suspend fun invoke(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): List<StoreOrdersDTO>
}