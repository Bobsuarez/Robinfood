@file:JvmName("OrderProductTaxesMappers")
package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderProductTaxDTO
import com.robinfood.core.dtos.GetOrderDetailFinalProductTaxDTO
import com.robinfood.core.entities.OrderProductTaxEntity

fun OrderProductTaxEntity.toOrderProductTaxDTO(): OrderProductTaxDTO {
    return OrderProductTaxDTO(
        articleId,
        articleTypeId,
        dicTaxId,
        familyTaxTypeId,
        id,
        orderFinalProductId,
        orderId,
        taxPrice,
        taxTypeId,
        taxTypeName,
        taxValue
    )
}

fun OrderProductTaxDTO.toGetOrderDetailFinalProductTaxDTO(): GetOrderDetailFinalProductTaxDTO {
    return GetOrderDetailFinalProductTaxDTO(
        id,
        dicTaxId,
        taxTypeId,
        taxTypeName,
        taxValue,
        familyTaxTypeId,
        taxPrice,
        orderFinalProductId
    );
}