package com.robinfood.repository.customerinvoice

import com.robinfood.core.dtos.customerinvoice.OrderInvoiceDTO
import com.robinfood.core.dtos.customerinvoice.PrintFormatDTO
import com.robinfood.core.entities.customerinvoice.OrderInvoiceEntity
import com.robinfood.core.enums.Result

/**
 * Repository for get the info order customer invoice
 */
interface IOrderCustomerInvoiceRepository {

    /**
     * Retrieves the info customer invoice
     * [orderId] order id request
     * [token] the authentication token to be used
     * @return the info customer invoice
     */
    suspend fun getOrderCustomerInvoiceDetail(
        orderId: Long,
        token: String
    ): Result<PrintFormatDTO>
}