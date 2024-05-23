package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.SubTotalInvoiceDTO
import com.robinfood.core.entities.customerinvoice.SubTotalInvoiceEntity

fun SubTotalInvoiceEntity.toSubTotalInvoiceDTO(): SubTotalInvoiceDTO {
    return SubTotalInvoiceDTO(
        name = name,
        value = value
    )
}
