package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.OptionProductInvoiceDTO
import com.robinfood.core.entities.customerinvoice.OptionProductInvoiceEntity

fun OptionProductInvoiceEntity.toOptionProductInvoiceDTO(): OptionProductInvoiceDTO {
    return OptionProductInvoiceDTO(
        name = name,
        nameGroup = nameGroup,
        price = price,
        quantity = quantity,
        symbol = symbol
    )
}
