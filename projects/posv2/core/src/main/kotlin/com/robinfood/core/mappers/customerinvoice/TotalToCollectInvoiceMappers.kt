package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.TotalToCollectInvoiceDTO
import com.robinfood.core.entities.customerinvoice.TotalToCollectInvoiceEntity

fun TotalToCollectInvoiceEntity.toTotalToCollectInvoiceDTO(): TotalToCollectInvoiceDTO {
    return TotalToCollectInvoiceDTO(
        currency = currency,
        title = title,
        value = value
    )
}
