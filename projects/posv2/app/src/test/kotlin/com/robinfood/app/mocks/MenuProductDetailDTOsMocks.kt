package com.robinfood.app.mocks

import com.robinfood.core.dtos.MenuBrandDTO
import com.robinfood.core.dtos.menu.response.CategoryDTO
import com.robinfood.core.dtos.menu.response.MenuGroupPortionDTO
import com.robinfood.core.dtos.menu.response.MenuGroupProductDTO
import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailCrossSellingDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.dtos.menu.response.MenuProductGroupDTO
import com.robinfood.core.dtos.menu.response.MenuProductSizeDTO
import com.robinfood.core.dtos.menu.response.MenuSizeGroupDTO
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionDTO
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionDataDTO
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionResponseDTO
import com.robinfood.core.entities.menu.MenuArticleDTO
import java.math.BigDecimal

class MenuProductDetailDTOsMocks {

    val skuTest = "skuTest"

    private val suggestedPortion = MenuSuggestedPortionDataDTO(
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.jpg",
            "Carne molida",
            1L,
            "sku",
            1L,
            150.0
    )

    private val portion = MenuGroupPortionDTO(
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
            1,
            1.0
    )

    private val portionSkuTest = MenuGroupPortionDTO(
            true,
            BigDecimal.ZERO,
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Carne molida",
            1L,
            BigDecimal.ZERO,
            BigDecimal(9800),
            1,
            skuTest,
            1,
            1.0
    )

    private val group = MenuProductGroupDTO(
            0,
            1L,
            1L,
            2,
            1,
            "Tests",
            "Test",
            listOf(portion).toMutableList(),
            "Tests",
            "Test",
            "sku",
            0
    )

    private val groupSkuTest = MenuProductGroupDTO(
            0,
            1L,
            1L,
            2,
            1,
            "Tests",
            "Test",
            listOf(portionSkuTest).toMutableList(),
            "Tests",
            "Test",
            skuTest,
            0
    )

    val menuProductResponseDTO = MenuHallProductResponseDTO(
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
            "ARTICLE"
    )

    val menuProductResponseDTOSkutTest = MenuHallProductResponseDTO(
            1L,
            1L,
            "Product",
            BigDecimal.ZERO,
            1L,
            listOf(groupSkuTest).toMutableList(),
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Papas",
            1L,
            1,
            BigDecimal(9800),
            1L,
            "suggested",
            1L,
            skuTest,
            listOf("tag"),
            1L,
            "ARTICLE"
    )

    val suggestedPortions = MenuSuggestedPortionResponseDTO(
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.jpg",
            "Carne molida",
            1L,
            "sku",
            listOf(suggestedPortion).toMutableList(),
    )

    private val menuSuggestedPortionDTO = MenuSuggestedPortionDTO(
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.jpg",
            "Carne molida",
            1L,
            "sku",
            1L,
            150.0
    )

    private val menuGroupProductDTO = MenuGroupProductDTO(
            false,
            BigDecimal.ZERO,
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            true,
            "Carne molida",
            1L,
            BigDecimal(9800),
            2,
            "sku",
            listOf(menuSuggestedPortionDTO).toMutableList(),
            1L,
            1.0
    )

    private val menuSizeGroupDTO = MenuSizeGroupDTO(
            1L,
            "Test",
            "Tests",
            "Test",
            listOf(menuGroupProductDTO).toMutableList(),
            0,
            2,
            1,
            "Tests",
            "Test",
            "sku",
            0
    )


    val menuProductSizeDTO = MenuProductSizeDTO(
            MenuArticleDTO(
                    1L,
                    1L,
                    1L,
                    "ARTICLE"
            ),
            BigDecimal.ZERO,
            listOf(menuSizeGroupDTO).toMutableList(),
            1L,
            "MUY",
            BigDecimal(9800)
    )

    val menuProductDetailDTO = MenuProductDetailDTO(
            MenuBrandDTO(
                    1L,
                    "RobinFood"
            ),
            "Product",
            1,
            "suggested",
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Papas",
            1L,
            listOf(menuProductSizeDTO).toMutableList(),
            "sku",
    )

    val menuProductDetailCrossSellingDTO = MenuProductDetailCrossSellingDTO(
            MenuBrandDTO(
                    1L,
                    "MUY"
            ),
            CategoryDTO(
                    1L,
                    "Postres",
            ),
            "Product",
            "suggested",
            1L,
            "https://s3.us-west-1.amazonaws.com/files-muy/photos/menus/elements/thumbnails/image.png",
            "Papas",
            listOf(menuProductSizeDTO).toMutableList(),
            "sku",
    )
}