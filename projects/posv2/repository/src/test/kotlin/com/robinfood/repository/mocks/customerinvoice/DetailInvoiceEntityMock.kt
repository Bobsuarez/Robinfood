package com.robinfood.repository.mocks.customerinvoice

import com.robinfood.core.entities.customerinvoice.DetailInvoiceEntity

class DetailInvoiceEntityMock {

    val detailInvoiceEntityMock = DetailInvoiceEntity(
        "$",
        DiscountInvoiceEntityMock().discountInvoiceEntityMock,
        SubTotalInvoiceEntityMock().subTotalInvoiceEntityMock,
        TaxInvoiceEntityMock().taxInvoiceEntityMock,
        "Valor productos",
        TotalInvoiceEntityMock().totalInvoiceEntityMock,
        TypeTaxInvoiceEntityMock().typeTaxInvoiceEntityMock
    )
}