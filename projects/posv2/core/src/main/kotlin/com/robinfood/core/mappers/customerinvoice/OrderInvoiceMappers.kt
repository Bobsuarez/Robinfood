package com.robinfood.core.mappers.customerinvoice

import com.robinfood.core.dtos.customerinvoice.OrderInvoiceDTO
import com.robinfood.core.entities.customerinvoice.OrderInvoiceEntity

fun OrderInvoiceEntity.toOrderInvoiceDTO(): OrderInvoiceDTO {
    return OrderInvoiceDTO(
        additionalNotes = additionalNotes,
        brandId = brandId,
        brandLogo = brandLogo,
        cashPaymentName = cashPaymentName,
        cashPaymentValue = cashPaymentValue,
        code = code,
        codeCaja = codeCaja,
        compensation = compensation.toCompensationInvoiceDTO(),
        consCaja = consCaja,
        copyOrderCommand = copyOrderCommand,
        copyOrderTicket = copyOrderTicket,
        detail = detail.toDetailInvoiceDTO(),
        discount = discount,
        externalOrderNumber = externalOrderNumber,
        franchiseName = franchiseName,
        hasCompensation = hasCompensation,
        id = id,
        infAddress = infAddress,
        infCity = infCity,
        infNit = infNit,
        isDelivery = isDelivery,
        isExternalDelivery = isExternalDelivery,
        isWalkers = isWalkers,
        multiBrand = multiBrand,
        nameCommand = nameCommand,
        nameCustomer = nameCustomer,
        orderBrandId = orderBrandId,
        orderNumber = orderNumber,
        phoneCustomer = phoneCustomer,
        paymentMethod = paymentMethod,
        prepaid = prepaid,
        prepaidName = prepaidName,
        prepaidValue = prepaidValue,
        printType = printType,
        products = products.mapNotNull { productEntity ->
            productEntity.toProductInvoiceDTO()
        },
        resDateInit = resDateInit,
        resDateEnd = resDateEnd,
        reception = reception,
        resNumber = resNumber,
        resNumberInit = resNumberInit,
        resNumberEnd = resNumberEnd,
        resTypeNumber = resTypeNumber,
        services = services.mapNotNull { servicesEntity ->
            servicesEntity.toServicesDTO()
        },
        storeId = storeId,
        storeName = storeName,
        subtotal = subtotal,
        tax = tax,
        toGo = toGo,
        total = total,
        totalToCollect = totalToCollect.toTotalToCollectInvoiceDTO(),
        totalToPay = totalToPay.toTotalToPayInvoiceDTO(),
        typeIdent = typeIdent,
        typeLabel = typeLabel,
        uid = uid,
        userId = userId,
        userLoyaltyStatus = userLoyaltyStatus,
        wherePrinter = wherePrinter,
        wifiVoucher = wifiVoucher
    )
}
