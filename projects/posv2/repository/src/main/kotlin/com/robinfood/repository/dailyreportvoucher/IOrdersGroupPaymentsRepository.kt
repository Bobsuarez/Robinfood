package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderGroupPaymentDTO
import com.robinfood.core.enums.Result

/**
 * Repository for order group payments related data
 */
interface IOrdersGroupPaymentsRepository {

    /**
     * Retrieves the order group categories
     * [dailyReportVoucherRequestDTO] properties request
     * [token] the authentication token to be used
     * @return the active delivery platforms for the store
     */
    suspend fun getOrderGroupPayments(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): Result<List<OrderGroupPaymentDTO>>
}