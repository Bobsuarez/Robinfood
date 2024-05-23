package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.ServicesInvoiceDTO
import com.robinfood.core.entities.customerinvoice.ServicesInvoiceEntity

fun ServicesInvoiceEntity.toServicesDTO(): ServicesInvoiceDTO {
    return ServicesInvoiceDTO(
        currency = currency,
        discount = discount.toDiscountInvoiceDTO(),
        name = name,
        subtotal = subtotal.toSubTotalInvoiceDTO(),
        tax = tax.toTaxInvoiceDTO(),
        total = total.toTotalInvoiceDTO()
    )
}
