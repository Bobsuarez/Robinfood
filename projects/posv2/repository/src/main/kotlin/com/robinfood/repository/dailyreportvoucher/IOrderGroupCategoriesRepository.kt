package com.robinfood.repository.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO
import com.robinfood.core.enums.Result

/**
 * Repository for order group categories related data
 */
interface IOrderGroupCategoriesRepository {

    /**
     * Retrieves the order group categories
     * [dailyReportVoucherRequestDTO] properties request
     * [token] the authentication token to be used
     * @return the active delivery platforms for the store
     */
    suspend fun getOrderGroupCategories(dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
                                         token: String
    ): Result<List<OrderCategoryDTO>>
}