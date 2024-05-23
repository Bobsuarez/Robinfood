package com.robinfood.app.mocks.customerinvoice

import com.robinfood.core.dtos.customerinvoice.ProductInvoiceDTO

class ProductInvoiceDTOMock {

    val productInvoiceEntityMocks = listOf(
        ProductInvoiceDTO(
            1,
            0.00,
            "Fresco",
            OptionProductInvoiceDTOMock().optionProductInvoiceDTOMock,
            12900.00,
            1435,
            1,
            13600.00,
            13600.00
        )
    )
}