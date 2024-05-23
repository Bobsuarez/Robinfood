package com.robinfood.repository.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.witnesstape.toStoreResolutionEntityToPosResolutionDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class StoreResolutionSequenceRepository(
    private val orderCreationQueriesAPI: OrderCreationQueriesAPI,
    private val dispatchers: DispatcherProvider
) : IStoreResolutionSequenceRepository {

    override suspend fun getStoreResolutionSequence(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): Result<List<PosResolutionSequenceDTO>> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    orderCreationQueriesAPI.getStoreResolutionSequence(
                        token,
                        storeOrderRequestDTO.timeZone,
                        storeOrderRequestDTO.storeId,
                        storeOrderRequestDTO.localDateStart,
                        storeOrderRequestDTO.localDateEnd
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val storeResolutionSequenceDTOs = result.data.data
                    if (storeResolutionSequenceDTOs == null) {
                        Result.Error(
                            OrchestratorException("Orders Store Sequence response is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val storeResolutionSequenceDTOs =
                            storeResolutionSequenceDTOs.mapNotNull { storeResolutionSequenceEntity ->
                                storeResolutionSequenceEntity.toStoreResolutionEntityToPosResolutionDTO()
                            }
                        Result.Success(storeResolutionSequenceDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}