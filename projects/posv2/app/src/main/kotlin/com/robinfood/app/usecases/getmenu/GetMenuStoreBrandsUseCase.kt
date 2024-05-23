package com.robinfood.app.usecases.getmenu

import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository

/**
 * Implementation of IGetMenuStoreBrandsUseCase
 */
class GetMenuStoreBrandsUseCase(
        private val menuRepository: IMenuRepository
) : IGetMenuStoreBrandsUseCase {

    override suspend fun invoke(
            token: String,
            storeId: Long
    ): Result<List<MenuBrandResponseDTO>> {
        return menuRepository.getStoreBrands(token, storeId)
    }
}