package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.DetailInvoiceDTO
import com.robinfood.core.entities.customerinvoice.DetailInvoiceEntity

fun DetailInvoiceEntity.toDetailInvoiceDTO(): DetailInvoiceDTO {
    return DetailInvoiceDTO(
        currency = currency,
        discount = discount.toDiscountInvoiceDTO(),
        subtotal = subtotal.toSubTotalInvoiceDTO(),
        tax = tax.toTaxInvoiceDTO(),
        title = title,
        total = total.toTotalInvoiceDTO(),
        typeTax = typeTax.toTypeTaxInvoiceDTO()
    )
}
