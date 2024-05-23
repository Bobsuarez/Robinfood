package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository

/**
 * Implementation of IGetChangePortionsUseCase
 */
class GetSuggestedPortionUseCase(
        private val menuRepository: IMenuRepository
):IGetSuggestedPortionsUseCase {

    override suspend fun invoke(
            token: String,
            portionIds: List<Long>
    ): Result<List<MenuSuggestedPortionResponseDTO>> {
        return menuRepository.getSuggestedPortions(token, portionIds)
    }
}