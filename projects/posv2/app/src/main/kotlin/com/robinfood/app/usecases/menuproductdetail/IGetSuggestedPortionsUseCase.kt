package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionResponseDTO
import com.robinfood.core.enums.Result

/**
 *  Use case that retrieves the suggested portions
 */
interface IGetSuggestedPortionsUseCase {

    /**
     * Retrieve the suggested portions
     * [token] the authentication token to be used
     * [portionIds] list with the portions identifier
     * @return the change portions
     */
    suspend fun invoke(
            token: String,
            portionIds: List<Long>
    ): Result<List<MenuSuggestedPortionResponseDTO>>
}