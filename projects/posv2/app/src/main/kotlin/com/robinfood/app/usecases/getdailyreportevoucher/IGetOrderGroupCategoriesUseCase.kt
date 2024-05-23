package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO

/**
 * Use case Order Group Categories
 */
interface IGetOrderGroupCategoriesUseCase {

    /**
     * Sends a request to get Group Categories
     * @param token the authentication token
     * @param dailyReportVoucherRequestDTO request for Group Categories
     * @return the List Order Group Categories
     */
    suspend fun invoke(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): List<OrderCategoryDTO>
}