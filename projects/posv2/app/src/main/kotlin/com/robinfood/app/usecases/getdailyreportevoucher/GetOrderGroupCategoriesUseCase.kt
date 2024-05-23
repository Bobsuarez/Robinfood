package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import com.robinfood.core.dtos.dailyreportvoucher.OrderCategoryDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.dailyreportvoucher.IOrderGroupCategoriesRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetOrderGroupCategoriesUseCase(
    private val orderGroupCategoriesRepository: IOrderGroupCategoriesRepository
) : IGetOrderGroupCategoriesUseCase {

    override suspend fun invoke(
        dailyReportVoucherRequestDTO: DailyReportVoucherRequestDTO,
        token: String
    ): List<OrderCategoryDTO> {

        val orderGroupCategoriesResult = orderGroupCategoriesRepository.getOrderGroupCategories(
            dailyReportVoucherRequestDTO,
            token
        )

        return when (orderGroupCategoriesResult) {
            is Result.Error -> throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
            is Result.Success -> return orderGroupCategoriesResult.data
        }
    }

}