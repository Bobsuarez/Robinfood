package com.robinfood.app.usecases.witnesstape

import com.robinfood.core.dtos.report.ReportResponseDTO
import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO

interface IGetWitnessTapeReportUseCase {

    suspend fun invoke(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): ReportResponseDTO

}