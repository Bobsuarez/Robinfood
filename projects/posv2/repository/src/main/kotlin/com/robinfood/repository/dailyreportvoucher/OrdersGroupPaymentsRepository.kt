package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderGroupPaymentDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.dailyreportvoucher.toOrderGroupPaymentDTO
import com.robinfood.network.api.OrderCreationQueriesAPI
import com.robinfood.network.di.DispatcherProvider
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class OrdersGroupPaymentsRepository(
    private val orderCreationQueriesAPI: OrderCreationQueriesAPI,
    private val dispatchers: DispatcherProvider
) : IOrdersGroupPaymentsRepository {

    override suspend fun getOrderGroupPayments(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): Result<List<OrderGroupPaymentDTO>> {

        return withContext(dispatchers.io) {
            val result = safeApiCall(
                call = {
                    orderCreationQueriesAPI.getOrderGroupPayments(
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
                    val orderGroupPaymentsEntities = result.data.data
                    if (orderGroupPaymentsEntities == null) {
                        Result.Error(
                            OrchestratorException("Groups Payments response is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val orderGroupPaymentsDTOs =
                            orderGroupPaymentsEntities.mapNotNull { orderGroupPayemntEntity ->
                                orderGroupPayemntEntity.toOrderGroupPaymentDTO()
                            }
                        Result.Success(orderGroupPaymentsDTOs)
                    }
                }
                is Result.Error -> result
            }
        }
    }
}