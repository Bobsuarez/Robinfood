package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.PrintFormatDTO
import com.robinfood.core.entities.customerinvoice.PrintFormatEntity

fun PrintFormatEntity.toPrintFormatDTO(): PrintFormatDTO {
    return PrintFormatDTO(
        company = company.toCompanyInvoiceDTO(),
        order = order.toOrderInvoiceDTO(),
        printer = printer.toPrintDTO()
    )
}
