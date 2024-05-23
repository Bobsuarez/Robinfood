package com.robinfood.core.mappers

import com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE
import com.robinfood.core.dtos.OrderDetailBrandDTO
import com.robinfood.core.dtos.OrderDetailCategory
import com.robinfood.core.dtos.OrderDetailProductDTO
import com.robinfood.core.dtos.OrderDetailSizeDTO
import com.robinfood.core.entities.OrderDetailProductArticleDTO
import com.robinfood.core.entities.OrderDetailProductEntity
import java.math.BigDecimal

fun OrderDetailProductEntity.toOrderDetailProductDTO(): OrderDetailProductDTO? {
    return if (id == null) {
        null
    } else {
        return OrderDetailProductDTO(
                OrderDetailProductArticleDTO(
                        id = articleId ?: DEFAULT_LONG_VALUE,
                        menuHallProductId = menuHallProductId ?: DEFAULT_LONG_VALUE,
                        typeId = articleTypeId ?: DEFAULT_LONG_VALUE,
                        typeName = articleName.orEmpty()
                ),
                basePrice = basePrice ?: BigDecimal.ZERO,
                brand = OrderDetailBrandDTO(
                        id = brandMenuId ?: DEFAULT_LONG_VALUE,
                        name = brandName.orEmpty()
                ),
                category = OrderDetailCategory(
                        id = categoryId ?: DEFAULT_LONG_VALUE,
                        name = categoryName.orEmpty()
                ),
                co2Total = co2Total ?: BigDecimal.ZERO,
                discounts = discounts?.mapNotNull { orderDetailProductDiscountEntity ->
                    orderDetailProductDiscountEntity.toOrderDetailProductDiscountDTO()
                } ?: emptyList(),
                displayType = displayType,
                id = id,
                image = image.orEmpty(),
                name = name.orEmpty(),
                quantity = quantity ?: DEFAULT_INTEGER_VALUE,
                size = OrderDetailSizeDTO(
                        id = sizeId ?: DEFAULT_LONG_VALUE,
                        groups = groups?.mapNotNull { orderDetailProductGroupEntity ->
                            orderDetailProductGroupEntity.toOrderDetailProductGroupDTO()
                        } ?: emptyList(),
                        name = sizeName.orEmpty(),
                        suggestedGroups = emptyList()
                ),
                sku = sku.orEmpty(),
                taxes = taxes?.mapNotNull { orderDetailProductTaxEntity ->
                    orderDetailProductTaxEntity.toOrderDetailProductTaxDTO()
                } ?: emptyList(),
                unitPrice = unitPrice ?: BigDecimal.ZERO
        )
    }
}