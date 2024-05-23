package com.robinfood.app.usecases.getmenu

import com.robinfood.core.dtos.menu.MenuDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the menu
 */
interface IGetMenuUseCase {

    /**
     * Retrieves the menu based on the following query params
     * [token] the authentication token to be used
     * [countryId] the store country identification
     * [flowId] flow identification
     * [storeId] the store identification to get menu
     * @return the active menu for the store
     */
    suspend fun invoke(
            token: String,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): Result<MenuDTO>
}