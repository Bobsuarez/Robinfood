package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.TotalToPayInvoiceDTO
import com.robinfood.core.entities.customerinvoice.TotalToPayInvoiceEntity

fun TotalToPayInvoiceEntity.toTotalToPayInvoiceDTO(): TotalToPayInvoiceDTO {
    return TotalToPayInvoiceDTO(
        currency = currency,
        title = title,
        value = value
    )
}
