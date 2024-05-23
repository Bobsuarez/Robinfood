package com.robinfood.app.mappers

import com.robinfood.core.dtos.OrderFinalProductDTO
import com.robinfood.core.entities.OrderFinalProductEntity

fun OrderFinalProductEntity.toOrderFinalProductDTO(): OrderFinalProductDTO {

    return OrderFinalProductDTO(
            articleId,
            articleTypeId,
            brandId,
            brandMenuId,
            brandName,
            basePrice,
            companyId,
            createdAt,
            discountPrice,
            finalProductCategoryId,
            finalProductCategoryName,
            finalProductId,
            finalProductName,
            id,
            image,
            orderId,
            productsPrice,
            quantity,
            sizeId,
            sizeName,
            co2Total,
            totalPriceNt,
            totalTaxPrice,
            updatedAt
    )
}
