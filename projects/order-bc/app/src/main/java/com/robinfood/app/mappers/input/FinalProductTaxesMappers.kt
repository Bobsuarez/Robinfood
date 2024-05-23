@file:JvmName("InputFinalProductTaxesMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.dtos.request.order.FinalProductTaxDTO
import com.robinfood.core.entities.OrderProductTaxEntity

fun FinalProductTaxDTO.toOrderProductTaxEntity(): OrderProductTaxEntity {
    return OrderProductTaxEntity(
            articleId,
            articleTypeId,
            null,
            taxId,
            familyId,
            id,
            orderFinalProductId,
            orderId,
            taxPrice,
            taxTypeId,
            taxTypeName,
            taxValue,
            null
    )
}
