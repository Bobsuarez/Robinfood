package com.robinfood.app.usecases.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.witnesstape.IOrdersByStoreRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetOrdersByStoreUseCase(
    private val ordersByStoreRepository: IOrdersByStoreRepository
) : IGetOrdersByStoreUseCase {

    override suspend fun invoke(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): List<StoreOrdersDTO> {

        val orderByStoreResult = ordersByStoreRepository.getOrdersByStore(
            storeOrderRequestDTO,
            token
        )

        return when (orderByStoreResult) {
            is Result.Error -> throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
            is Result.Success -> return orderByStoreResult.data
        }
    }

}