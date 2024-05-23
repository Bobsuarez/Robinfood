package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderGroupPaymentDTO

/**
 * Use case Order Group Payments
 */
interface IGetOrderGroupPaymentsUseCase {

    /**
     * Sends a request to get report pdf
     * @param token the authentication token
     * @param dailyReportVoucherRequestDTO request for get Group Payment
     * @return the List Order Group Payments
     */
    suspend fun invoke(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): List<OrderGroupPaymentDTO>
}