package com.robinfood.repository.mocks

import com.robinfood.core.entities.menu.*
import java.math.BigDecimal

class MenuProductDetailEntitiesMocks {

    private val suggestedPortion = MenuSuggestedPortionDataEntity(
            1L,
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.jpg",
            "Carne molida",
            1L,
            150.0,
            "sku"
    )

    private val portion = MenuGroupPortionEntity(
            true,
            BigDecimal.ZERO,
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Carne molida",
            1L,
            BigDecimal.ZERO,
            BigDecimal(9800),
            1,
            "sku",
            1L,
            1.0
    )

    private val group = MenuProductGroupEntity(
            1,
            1L,
            1L,
            2,
            0,
            "Tests",
            "Test",
            listOf(portion).toMutableList(),
            "Tests",
            "Test",
            "sku",
            0
    )


    val menuProductResponseEntity = MenuHallProductResponseEntity(
            1L,
            1L,
            "Product",
            BigDecimal.ZERO,
            1L,
            listOf(group).toMutableList(),
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Papas",
            1L,
            1,
            BigDecimal(9800),
            1L,
            "suggested",
            1L,
            "sku",
            listOf("tag"),
            1L,
            "test"
    )

    val menuProductEmptyGroupResponseEntity = MenuHallProductResponseEntity(
            1L,
            1L,
            "Product",
            BigDecimal.ZERO,
            1L,
            listOf(),
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Papas",
            1L,
            1,
            BigDecimal(9800),
            1L,
            "suggested",
            1L,
            "sku",
            listOf("tag"),
            1L,
            "test"
    )

    val menuProductEmptyDataResponseEntity = MenuHallProductResponseEntity(
            null,
            null,
            null,
            null,
            null,
            null,
            1L,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    )

    val menuProductNullDataResponseEntity = MenuHallProductResponseEntity(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    )

    val suggestedPortions = MenuSuggestedPortionResponseEntity(
            listOf(suggestedPortion).toMutableList(),
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.jpg",
            "Carne molida",
            1L,
            "sku"
    )

    val suggestedEmptyPortions = MenuSuggestedPortionResponseEntity(
            listOf(),
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.jpg",
            "Carne molida",
            1L,
            "sku"
    )
}