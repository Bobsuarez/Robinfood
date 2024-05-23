package com.robinfood.core.mappers.witnesstape

import com.robinfood.core.constants.GlobalConstants.BRAZIL
import com.robinfood.core.constants.GlobalConstants.FORMAT_DOUBLE_TWO_DECIMAL
import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO
import com.robinfood.core.dtos.witnesstape.WitnessTapeTableSalesDTO
import com.robinfood.core.entities.witnesstape.StoreOrdersEntity
import com.robinfood.core.enums.OrderStatusEnum

fun StoreOrdersEntity.toStoreOrdersDTO(): StoreOrdersDTO? {
    return if (id == null) {
        return null
    } else StoreOrdersDTO(
        compensation = compensation,
        discounts = discounts,
        grossValue = grossValue,
        id = id,
        netValue = netValue,
        orderInvoiceNumber = orderInvoiceNumber,
        posId = posId,
        statusCode = statusCode,
        statusId = statusId,
        taxes = taxes,
        uuid = uuid
    )
}

fun StoreOrdersDTO.toWitnessTapeTableSalesDTO(countryId: Long): WitnessTapeTableSalesDTO {
    return WitnessTapeTableSalesDTO(
        box = "",
        compensationCo2 = FORMAT_DOUBLE_TWO_DECIMAL.format(compensation).toDouble(),
        discounts = FORMAT_DOUBLE_TWO_DECIMAL.format(discounts).toDouble(),
        docEquivalent = orderInvoiceNumber ?: "",
        posId = posId,
        prefix = "",
        status = if (countryId.toInt() == BRAZIL) OrderStatusEnum.valueOfIdStatus(statusCode).groupValueBrazil
        else OrderStatusEnum.valueOfIdStatus(statusCode).groupValue,
        subTotal = FORMAT_DOUBLE_TWO_DECIMAL.format(grossValue).toDouble(),
        taxes = FORMAT_DOUBLE_TWO_DECIMAL.format(taxes).toDouble(),
        total = FORMAT_DOUBLE_TWO_DECIMAL.format(netValue).toDouble()
    )
}
