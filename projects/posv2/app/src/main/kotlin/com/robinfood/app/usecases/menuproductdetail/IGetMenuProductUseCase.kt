package com.robinfood.app.usecases.menuproductdetail;

import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.enums.Result

/**
 * Use case that retrieves the product info from menu repository
 */
interface IGetMenuProductUseCase {

    /**
     * Retrieves the product info
     * [token] the authentication token to be used
     * [countryId] country identifier of the product
     * [brandId] brand identifier of the product
     * [flowId] flow identifier of the product
     * [storeId] store identifier of the product
     * [productIds] the productId identifiers
     *
     * @return the products with detailed info
     */
    suspend fun invoke(
            token: String,
            countryId: Long,
            brandId: Long,
            flowId: Long,
            storeId: Long,
            platformId: Long?,
            productIds: List<Long>
    ): Result<List<MenuHallProductResponseDTO>>
}
