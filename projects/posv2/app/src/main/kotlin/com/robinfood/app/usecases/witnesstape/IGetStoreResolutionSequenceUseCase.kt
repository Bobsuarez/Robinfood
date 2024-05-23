package com.robinfood.app.usecases.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO

/**
 * Use case List Resolutions Pos by Store
 */
interface IGetStoreResolutionSequenceUseCase {

    /**
     * Sends a request to get Resolutions Pos by Store
     * @param storeOrderRequestDTO request store orders
     * @param token token of authorization
     * @return the List Resolutions Pos by Store
     */
    suspend fun invoke(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): List<PosResolutionSequenceDTO>
}