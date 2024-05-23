package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.dailyreportvoucher.toOrderCategoryDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class OrderGroupCategoriesRepository(
    private val orderCreationQueriesAPI: OrderCreationQueriesAPI,
    private val dispatchers: DispatcherProvider
) : IOrderGroupCategoriesRepository {
    override suspend fun getOrderGroupCategories(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): Result<List<OrderCategoryDTO>> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    orderCreationQueriesAPI.getOrderGroupCategories(
                        token,
                        dailyReportVoucherRequestDTO.timeZone,
                        dailyReportVoucherRequestDTO.posId,
                        dailyReportVoucherRequestDTO.localDateStart,
                        dailyReportVoucherRequestDTO.localDateEnd
                    ).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val orderGroupCategoriesEntities = result.data.data
                    if (orderGroupCategoriesEntities == null) {
                        Result.Error(
                            OrchestratorException("Groups Categories response is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val orderGroupCategoriesDTOs =
                            orderGroupCategoriesEntities.mapNotNull { orderGroupCategoryEntity ->
                                orderGroupCategoryEntity.toOrderCategoryDTO()
                            }
                        Result.Success(orderGroupCategoriesDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}