package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderGroupPaymentDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.dailyreportvoucher.IOrdersGroupPaymentsRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetOrderGroupPaymentsUseCase(
    private val ordersGroupPaymentsRepository: IOrdersGroupPaymentsRepository
) : IGetOrderGroupPaymentsUseCase {

    override suspend fun invoke(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): List<OrderGroupPaymentDTO> {

        val orderGroupPaymentsResult = ordersGroupPaymentsRepository.getOrderGroupPayments(
            dailyReportVoucherRequestDTO,
            token
        )

        return when (orderGroupPaymentsResult) {
            is Result.Error -> throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
            is Result.Success -> return orderGroupPaymentsResult.data
        }
    }

}