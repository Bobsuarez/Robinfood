package com.robinfood.repository.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.enums.Result

/**
 * Repository for get orders by store related data
 */
interface IStoreResolutionSequenceRepository {

    /**
     * Retrieves the orders by store
     * [storeOrderRequestDTO] properties request
     * [token] the authentication token to be used
     * @return the orders by store
     */
    suspend fun getStoreResolutionSequence(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): Result<List<PosResolutionSequenceDTO>>
}