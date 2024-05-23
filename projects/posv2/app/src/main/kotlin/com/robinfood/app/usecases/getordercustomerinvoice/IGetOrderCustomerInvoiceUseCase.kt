package com.robinfood.app.usecases.getordercustomerinvoice

import com.robinfood.core.dtos.customerinvoice.PrintFormatDTO

/**
 * Use case Order Customer Invoice
 */
interface IGetOrderCustomerInvoiceUseCase {

    /**
     * Sends a request to get Order Customer Invoice
     * @param orderId id of order customer invoice
     * @param token token of authorization
     * @return List Order Customer Invoice
     */
    suspend fun invoke(
        orderId: Long,
        token: String
    ): PrintFormatDTO?
}