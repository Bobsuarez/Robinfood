package com.robinfood.repository.orderhistory

import com.robinfood.core.dtos.HistoryDTO
import com.robinfood.core.dtos.orderhistory.OrderHistoryRequestDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.toHistoryDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

/**
 * Implementation of IOrderHistoryRepository
 */
@Repository
class OrderHistoryRepository(
    private val orderCreationQueriesAPI: OrderCreationQueriesAPI
): IOrderHistoryRepository {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun getOrderHistory(
        token: String,
        orderHistoryRequestDTO: OrderHistoryRequestDTO
    ): Result<HistoryDTO> {

        log.info("Init Order History Repository with token: {} and orderHistoryRequestDTO: {}",
                token, orderHistoryRequestDTO);

        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    orderCreationQueriesAPI.getOrderHistory(
                            orderHistoryRequestDTO.storeId,
                            orderHistoryRequestDTO.timeZone,
                            token,
                            orderHistoryRequestDTO.channelsId,
                            orderHistoryRequestDTO.currentPage,
                            orderHistoryRequestDTO.localDateStart,
                            orderHistoryRequestDTO.localDateEnd,
                            orderHistoryRequestDTO.perPage,
                            orderHistoryRequestDTO.isDelivery,
                            orderHistoryRequestDTO.searchText
                    ).callServices()
                }
            )

            log.info("This is the result: {}", result);
            when(result) {
                is Result.Success -> {
                    val historyEntity = result.data.data

                    if (historyEntity == null) {
                        Result.Error(OrchestratorException("History entity is null"), HttpStatus.NOT_FOUND)
                    } else {
                        val historyDTO = historyEntity.toHistoryDTO()
                        if (historyDTO == null) {
                            Result.Error(OrchestratorException("History dto is null"), HttpStatus.INTERNAL_SERVER_ERROR)
                        } else {
                            Result.Success(historyDTO)
                        }
                    }
                }
                is Result.Error -> result
            }
        }
    }
}