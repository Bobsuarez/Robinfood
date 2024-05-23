package com.robinfood.repository.mocks.customerinvoice

import com.robinfood.core.entities.customerinvoice.ProductInvoiceEntity

class ProductInvoiceEntityMock {

    val productInvoiceEntityMocks = listOf(
        ProductInvoiceEntity(
            1,
            0.00,
            "Fresco",
            OptionProductInvoiceEntityMock().optionProductInvoiceEntityMock,
            12900.00,
            1435,
            1,
            13600.00,
            13600.00
        )
    )
}