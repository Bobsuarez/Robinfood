package com.robinfood.core.entities.menu

import java.math.BigDecimal

data class MenuProductResponseEntity(
        val brandId: Long?,
        val countryId: Long?,
        val description: String?,
        val discount: BigDecimal?,
        val display: Int?,
        val displayType: Int?,
        val flow: String?,
        val id: Long?,
        val image: String?,
        val name: String?,
        val parentId: Long?,
        val position: Int?,
        val price: BigDecimal?,
        val sizeId: Long?,
        val sku: String?,
        val status: Long?,
        val typeId: Long?,
        val typeName: String?
)
