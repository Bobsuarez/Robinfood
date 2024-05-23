package com.robinfood.core.mappers.dailyreportvoucher

import com.robinfood.core.constants.GlobalConstants.FORMAT_DOUBLE_TWO_DECIMAL
import com.robinfood.core.dtos.dailyreportvoucher.OrderGroupPaymentDTO
import com.robinfood.core.dtos.dailyreportvoucher.PaymentMethodListDTO
import com.robinfood.core.entities.dailyreportvoucher.OrderGroupPaymentEntity

fun OrderGroupPaymentDTO.toPaymentMethodListDTO(): PaymentMethodListDTO? {
    return if (id == null) {
        return null
    } else PaymentMethodListDTO(
        name = name,
        transactionsQuantity = transactions,
        value = value
    )
}

fun OrderGroupPaymentEntity.toOrderGroupPaymentDTO(): OrderGroupPaymentDTO? {
    return if (id == null) {
        return null
    } else OrderGroupPaymentDTO(
        id = id,
        name = name,
        shortName = shortName,
        transactions = transactions,
        typeId = typeId,
        value = FORMAT_DOUBLE_TWO_DECIMAL.format(value).toDouble()
    )
}