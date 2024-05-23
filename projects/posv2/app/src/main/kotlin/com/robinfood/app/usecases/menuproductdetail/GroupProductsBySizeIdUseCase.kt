package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.core.dtos.MenuBrandDTO
import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.dtos.menu.response.MenuProductSizeDTO
import com.robinfood.core.dtos.menu.response.MenuSizeGroupDTO
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionResponseDTO
import com.robinfood.core.entities.menu.MenuArticleDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.mappers.menu.toMenuSizeGroupDTO
import com.robinfood.core.mappers.menu.toMenuSuggestedPortionDTO
import com.robinfood.core.utils.getSizeName
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

/**
 * Implementation of IGroupProductsBySizeIdUseCase
 */
class GroupProductsBySizeIdUseCase(
    private val getSuggestedPortionUseCase: IGetSuggestedPortionsUseCase
) : IGroupProductsBySizeIdUseCase {

    override suspend fun invoke(
        token: String,
        hallProducts: List<MenuHallProductResponseDTO>
    ): Result<MenuProductDetailDTO> {
        var portionIds = mutableListOf<Long>()

        for (product in hallProducts) {
            for (group in product.groups) {
                portionIds.addAll(group.portions.map { portion -> portion.id })
            }
        }

        portionIds = portionIds.toSet().toMutableList()

        val suggestedPortions = when (
            val resultMenuSuggestedPortions = getSuggestedPortionUseCase.invoke(token, portionIds)
        ) {
            is Result.Success -> resultMenuSuggestedPortions.data
            is Result.Error -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                resultMenuSuggestedPortions.exception.localizedMessage
            )
        }

        return with(hallProducts.first()) {
            Result.Success(
                MenuProductDetailDTO(
                    MenuBrandDTO(
                        brandId,
                        name
                    ),
                    description,
                    displayType,
                    productFlow,
                    parentId,
                    image,
                    name,
                    productCategoryId,
                    sizeDetail(hallProducts, suggestedPortions),
                    sku
                )
            )
        }
    }

    private fun sizeDetail(
        hallProducts: List<MenuHallProductResponseDTO>,
        suggestedPortions: List<MenuSuggestedPortionResponseDTO>
    ): MutableList<MenuProductSizeDTO> {
        val sizesDetail = mutableListOf<MenuProductSizeDTO>()

        for (resultProduct in hallProducts) {
            val groupsDetail = mutableListOf<MenuSizeGroupDTO>()
            for (group in resultProduct.groups) {
                val sizeGroup = group.toMenuSizeGroupDTO()
                groupsDetail.add(sizeGroup)
                sizeGroup(sizeGroup, suggestedPortions)
            }

            sizesDetail.add(
                MenuProductSizeDTO(
                    MenuArticleDTO(
                        resultProduct.articleId,
                        resultProduct.id,
                        resultProduct.type,
                        resultProduct.typeName
                    ),
                    resultProduct.discount,
                    groupsDetail,
                    resultProduct.sizeId,
                    getSizeName(resultProduct.sizeId),
                    resultProduct.price
                )
            )
        }

        return sizesDetail
    }

    private fun sizeGroup(sizeGroup: MenuSizeGroupDTO, suggestedPortions: List<MenuSuggestedPortionResponseDTO>) {
        for (portion in sizeGroup.portions) {
            val suggestedPortion = suggestedPortions.firstOrNull { it.id == portion.id }
            if (suggestedPortion != null) {
                portion.suggestedPortions = suggestedPortion.suggesteds.map { changePortion ->
                    changePortion.toMenuSuggestedPortionDTO()
                }
            }
        }
    }
}
