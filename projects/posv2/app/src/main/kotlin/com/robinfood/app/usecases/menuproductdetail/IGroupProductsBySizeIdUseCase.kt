package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the products grouped by size id
 */
interface IGroupProductsBySizeIdUseCase {

    /**
     * Group by size id the products
     * [token] the authentication token to be used
     * [hallProducts] list with the products
     * @return the products grouped by size id
     */
    suspend fun invoke(
            token: String,
            hallProducts: List<MenuHallProductResponseDTO>
    ): Result<MenuProductDetailDTO>
}