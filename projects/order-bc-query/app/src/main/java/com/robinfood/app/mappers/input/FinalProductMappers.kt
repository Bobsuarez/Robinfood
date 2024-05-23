@file:JvmName("InputFinalProductMappers")

package com.robinfood.app.mappers.input

import com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_EMPTY_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_EMPTY_VALUE
import com.robinfood.core.dtos.request.order.FinalProductDTO
import com.robinfood.core.entities.OrderFinalProductEntity

fun FinalProductDTO.toOrderFinalProductEntity(): OrderFinalProductEntity {
    return OrderFinalProductEntity(
            article.id,
            article.typeId,
            brand.franchiseId,
            brand.id,
            brand.name,
            value,
            DEFAULT_LONG_EMPTY_VALUE,
            DEFAULT_DOUBLE_EMPTY_VALUE,
            category.id,
            category.name,
            id,
            name,
            image,
            article.menuHallProductId,
            orderId!!,
            DEFAULT_DOUBLE_EMPTY_VALUE,
            quantity,
            size.id,
            size.name,
            DEFAULT_DOUBLE_EMPTY_VALUE,
            DEFAULT_DOUBLE_EMPTY_VALUE,
            DEFAULT_DOUBLE_EMPTY_VALUE
    )
}
