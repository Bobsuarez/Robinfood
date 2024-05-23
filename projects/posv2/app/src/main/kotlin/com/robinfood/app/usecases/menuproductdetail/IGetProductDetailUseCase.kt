package com.robinfood.app.usecases.menuproductdetail

import com.robinfood.core.dtos.menu.response.MenuProductDetailDTO
import com.robinfood.core.enums.Result

/**
 *  Use case that retrieves the product detail with the change portions
 */
interface IGetProductDetailUseCase {

    /**
     * Retrieve the product detail with change portions
     *
     * [token] the authentication token to be used
     * [countryId] country identifier of the product
     * [brandId] brand identifier of the product
     * [flowId] flow identifier of the product
     * [storeId] store identifier of the product
     * [productIds] list with the products identifier
     *
     * @return the product detail
     */
    suspend fun invoke(
            token: String,
            countryId: Long,
            brandId: Long,
            flowId: Long,
            storeId: Long,
            platformId: Long?,
            productIds: List<Long>
    ): Result<MenuProductDetailDTO>
}