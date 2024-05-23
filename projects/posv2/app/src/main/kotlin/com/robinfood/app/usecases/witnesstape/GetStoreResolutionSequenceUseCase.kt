package com.robinfood.app.usecases.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.witnesstape.IStoreResolutionSequenceRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetStoreResolutionSequenceUseCase(
    private val storeResolutionSequenceRepository: IStoreResolutionSequenceRepository
) : IGetStoreResolutionSequenceUseCase {

    override suspend fun invoke(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): List<PosResolutionSequenceDTO> {

        val orderByStoreResult = storeResolutionSequenceRepository.getStoreResolutionSequence(
            storeOrderRequestDTO,
            token
        )

        return when (orderByStoreResult) {
            is Result.Error -> throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
            is Result.Success -> return orderByStoreResult.data
        }
    }
}