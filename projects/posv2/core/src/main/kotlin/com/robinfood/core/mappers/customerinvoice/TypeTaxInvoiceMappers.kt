package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.TypeTaxInvoiceDTO
import com.robinfood.core.entities.customerinvoice.TypeTaxInvoiceEntity

fun TypeTaxInvoiceEntity.toTypeTaxInvoiceDTO(): TypeTaxInvoiceDTO {
    return TypeTaxInvoiceDTO(
        name = name,
        value = value
    )
}
