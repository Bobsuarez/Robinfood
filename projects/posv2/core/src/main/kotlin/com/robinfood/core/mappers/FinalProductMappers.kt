package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.FinalProductDTO
import com.robinfood.core.entities.transactionrequest.FinalProductEntity
import java.math.BigDecimal

fun FinalProductDTO.toFinalProductEntity(): FinalProductEntity {
    return FinalProductEntity(
        article = article.toArticleEntity(),
        brand = brand.toBrandEntity(),
        co2Total = co2Total ?: BigDecimal.ZERO,
        discounts = discounts.map { discountDTO -> discountDTO.toDiscountEntity() },
        groups = groups.map { groupDTO -> groupDTO.toGroupEntity() },
        id = id,
        image = image,
        name = name,
        price = price,
        quantity = quantity,
        removedPortions = removedPortions.map { removedPortionDTO -> removedPortionDTO.toRemovedPortionEntity() },
        size = size.toSizeEntity(),
        sku = sku,
        taxes = taxes.map { taxDTO -> taxDTO.toTaxEntity() },
        totalPrice = totalPrice
    )
}