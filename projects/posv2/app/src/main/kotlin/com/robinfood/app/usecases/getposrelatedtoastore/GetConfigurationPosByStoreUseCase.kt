package com.robinfood.app.usecases.getposrelatedtoastore

import com.robinfood.core.dtos.posrelatedtoastore.StorePosDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.posrelatedtoastore.ConfigurationPosByStoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetConfigurationPosByStoreUseCase(
    private val configurationPosByStoreRepository: ConfigurationPosByStoreRepository
) : IGetConfigurationPosByStoreUseCase {

    override suspend fun invoke(
        storeId: Long,
        token: String
    ): List<StorePosDTO> {

        val posByStoreResult = configurationPosByStoreRepository.getPosRelatedToAStore(
            storeId,
            token
        )

        return when (posByStoreResult) {
            is Result.Error -> throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
            is Result.Success -> return posByStoreResult.data
        }
    }
}