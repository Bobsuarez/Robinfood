package com.robinfood.repository.menu

import com.robinfood.core.dtos.menu.MenuBrandDTO
import com.robinfood.core.dtos.menu.MenuBrandResponseDTO
import com.robinfood.core.dtos.menu.response.MenuHallProductResponseDTO
import com.robinfood.core.dtos.menu.response.MenuSuggestedPortionResponseDTO
import com.robinfood.core.enums.Result

/**
 * Repository for menu related data
 */
interface IMenuRepository {

    /**
     * Retrieves the brands active for the store
     * [token] the authentication token to be used
     * [storeId] the store identification to get menu
     * @return the active menu for the store
     */
    suspend fun getStoreBrands(
            token: String,
            storeId: Long
    ): Result<List<MenuBrandResponseDTO>>

    /**
     * Retrieves the brand menu
     * [token] the authentication token to be used
     * [menuBrandResponse] the brand identification to get menu
     * [countryId] the store country identification
     * [flowId] flow identification
     * [storeId] the store identification to get menu
     * @return the active menu for the store
     */
    suspend fun getStoreMenuBrand(
            token: String,
            menuBrandResponse: MenuBrandResponseDTO,
            countryId: Long,
            flowId: Long,
            storeId: Long
    ): Result<MenuBrandDTO>

    /**
     * Retrieves the product detail based on products identifiers given
     * [token] the authentication token to be used
     * [countryId] country identifier of the product
     * [brandId] brand identifier of the product
     * [flowId] flow identifier of the product
     * [storeId] store identifier of the product
     * [platformId] platform identifier of the product
     * [productIds] the final product identifiers
     * @return the product with detailed info
     */
    suspend fun getProductDetail(
            token: String,
            countryId: Long,
            brandId: Long,
            flowId: Long,
            storeId: Long,
            platformId: Long?,
            productIds: Long
    ): Result<MenuHallProductResponseDTO>

    /**
     * Retrieves the suggested portions detail based on portion identifiers given
     * [token] the authentication token to be used
     * [portionsIds] the portions identifiers
     * @return the suggested portions with detailed info
     */
    suspend fun getSuggestedPortions(
            token: String,
            portionsIds: List<Long>
    ): Result<List<MenuSuggestedPortionResponseDTO>>
}