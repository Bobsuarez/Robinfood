package com.robinfood.app.usecases.getmenu

import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the menu store brands list
 */
interface IGetMenuStoreBrandsUseCase {

    /**
     * Retrieves the menu store brands
     * [token] the authentication token to be used
     * [storeId] the store identification to get menu
     * @return the active menu for the store
     */
    suspend fun invoke(
            token: String,
            storeId: Long
    ): Result<List<MenuBrandResponseDTO>>
}