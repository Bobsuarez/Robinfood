package com.robinfood.app.usecases.getmenu

import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.dtos.menu.MenuDTO
import com.robinfood.core.enums.Result
import com.robinfood.repository.menu.IMenuRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/**
 * Implementation of IGetMenuUseCase
 */
class GetMenuUseCase(
        private val getMenuStoreBrandsUseCase: IGetMenuStoreBrandsUseCase,
        private val menuRepository: IMenuRepository
) : IGetMenuUseCase {
    override suspend fun invoke(
            token: String,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): Result<MenuDTO> {

        val brands = getMenuStoreBrandsUseCase.invoke(token, storeId)
        return when (brands) {
            is Result.Success -> getBrandMenus(brands, token, countryId, flowId, storeId)
            is Result.Error -> brands
        }
    }

    private suspend fun getBrandMenus(
            brands: Result.Success<List<MenuBrandResponseDTO>>,
            token: String,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): Result<MenuDTO> {
        
        return coroutineScope {
            val brandsMenusResults = brands.data.map { menuBrandResponseDTO: MenuBrandResponseDTO ->
                async {
                    val menuBrand = menuRepository.getStoreMenuBrand(
                            token,
                            menuBrandResponseDTO,
                            countryId,
                            flowId,
                            storeId
                    )
                    when (menuBrand) {
                        is Result.Success -> menuBrand.data
                        is Result.Error -> null
                    }
                }
            }.awaitAll()
            Result.Success(MenuDTO(brandsMenusResults.filterNotNull()))
        }
    }
}