package com.robinfood.app.usecases.getordercustomerinvoice

import com.robinfood.core.dtos.report.ReportResponseDTO

interface IOrderCustomerInvoiceUseCase {

    suspend fun invoke(
        orderId: Long,
        token: String
    ): ReportResponseDTO

}