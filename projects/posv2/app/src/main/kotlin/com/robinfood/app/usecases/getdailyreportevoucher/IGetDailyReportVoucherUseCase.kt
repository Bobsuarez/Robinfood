package com.robinfood.app.usecases.getdailyreportevoucher

import com.robinfood.core.dtos.report.ReportResponseDTO
import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO

interface IGetDailyReportVoucherUseCase {

    suspend fun invoke(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): ReportResponseDTO
}