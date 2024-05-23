package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.CompensationInvoiceDTO
import com.robinfood.core.entities.customerinvoice.CompensationInvoiceEntity

fun CompensationInvoiceEntity.toCompensationInvoiceDTO(): CompensationInvoiceDTO {
    return CompensationInvoiceDTO(
        compensationName = compensationName,
        copy = copy,
        currency = currency,
        title = title,
        value = value
    )
}
