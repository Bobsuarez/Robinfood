package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.TaxInvoiceDTO
import com.robinfood.core.entities.customerinvoice.TaxInvoiceEntity

fun TaxInvoiceEntity.toTaxInvoiceDTO(): TaxInvoiceDTO {
    return TaxInvoiceDTO(
        name = name,
        value = value
    )
}
