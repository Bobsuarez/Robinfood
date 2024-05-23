package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.TotalInvoiceDTO
import com.robinfood.core.entities.customerinvoice.TotalInvoiceEntity

fun TotalInvoiceEntity.toTotalInvoiceDTO(): TotalInvoiceDTO {
    return TotalInvoiceDTO(
        name = name,
        value = value
    )
}
