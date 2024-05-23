package com.robinfood.app.usecases.getstoreconfigurations

import com.robinfood.core.dtos.store.StoreConfigurationsDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.storeconfigurations.IStoreConfigurationRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

/**
 * Implementation of IGetStoreConfigurationUseCase
 */
@Service
class GetStoreConfigurationUseCase(
    private val storeConfigurationRepository: IStoreConfigurationRepository
) : IGetStoreConfigurationUseCase {

    override suspend fun invoke(
        storeId: Long,
        token: String
    ): StoreConfigurationsDTO {

        val storeConfigurationsResult = storeConfigurationRepository.getStoreConfiguration(
            token,
            storeId
        )

        return when (storeConfigurationsResult) {
            is Result.Error -> throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
            is Result.Success -> return storeConfigurationsResult.data
        }

    }

}