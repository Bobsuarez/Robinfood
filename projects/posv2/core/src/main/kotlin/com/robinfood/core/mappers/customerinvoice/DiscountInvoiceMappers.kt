package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.DiscountInvoiceDTO
import com.robinfood.core.entities.customerinvoice.DiscountInvoiceEntity

fun DiscountInvoiceEntity.toDiscountInvoiceDTO(): DiscountInvoiceDTO {
    return DiscountInvoiceDTO(
        name = name,
        value = value
    )
}
