package com.robinfood.repository.mocks.customerinvoice

import com.robinfood.core.entities.customerinvoice.PrintFormatEntity

class PrintFormatEntityMock {

    val printFormatEntityMock = PrintFormatEntity(
        CompanyInvoiceEntityMock().companyInvoiceEntity,
        OrderInvoiceEntityMock().orderInvoiceEntityMock,
        PrintEntityMock().printEntityMock
    )
}