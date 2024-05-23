package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.CompanyInvoiceDTO
import com.robinfood.core.entities.customerinvoice.CompanyInvoiceEntity

fun CompanyInvoiceEntity.toCompanyInvoiceDTO(): CompanyInvoiceDTO {
    return CompanyInvoiceDTO(
        address = address,
        city = city,
        franchiseName = franchiseName,
        id = id,
        logoTemplate = logoTemplate,
        name = name,
        regime = regime,
        taxSymbol = taxSymbol,
        tax = tax
    )
}
