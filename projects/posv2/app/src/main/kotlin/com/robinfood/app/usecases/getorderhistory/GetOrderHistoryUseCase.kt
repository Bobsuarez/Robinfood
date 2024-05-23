package com.robinfood.app.usecases.getorderhistory

import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.orderhistory.OrderHistoryRequestDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.orderhistory.IOrderHistoryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Implementation of IGetOrderHistoryUseCase
 */
class GetOrderHistoryUseCase(
        private val orderHistoryRepository: IOrderHistoryRepository
) : IGetOrderHistoryUseCase {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun invoke(
            token: String,
            orderHistoryRequestDTO: OrderHistoryRequestDTO
    ): Result<HistoryDTO> {

        log.info("Init Get Order History Use Case");

        return orderHistoryRepository.getOrderHistory(
                token,
                orderHistoryRequestDTO
        )
    }
}