package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.ProductInvoiceDTO
import com.robinfood.core.entities.customerinvoice.ProductInvoiceEntity

fun ProductInvoiceEntity.toProductInvoiceDTO(): ProductInvoiceDTO {
    return ProductInvoiceDTO(
        brandId = brandId,
        discount = discount,
        name = name,
        option = option.map { optionProductInvoiceEntity ->
            optionProductInvoiceEntity.toOptionProductInvoiceDTO()
        },
        price = price,
        productId = productId,
        quantity = quantity,
        subtotal = subtotal,
        total = total
    )
}
