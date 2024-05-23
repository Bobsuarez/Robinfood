package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.menu.BrandMenuResponseEntity
import com.robinfood.core.entities.menu.MenuBrandResponseEntity
import com.robinfood.core.entities.menu.MenuHallProductResponseEntity
import com.robinfood.core.entities.menu.MenuSuggestedPortionResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface provided to make menu orchestrator api calls using Retrofit
 */
interface MenuBCAPI {

    @GET("v1/menus/current")
    suspend fun getStoreBrandMenu(
            @Header("Authorization") token: String,
            @Query("brand_id") brandId: Long,
            @Query("country_id") countryId: Long,
            @Query("flow_id") flowId: Long,
            @Query("store_id") storeId: Long
    ): Response<APIResponseEntity<BrandMenuResponseEntity>>

    @GET("v1/stores/{id}/brands")
    suspend fun getStoreBrands(
            @Header("Authorization") token: String,
            @Path("id") id: Long
    ): Response<APIResponseEntity<List<MenuBrandResponseEntity>>>

    /**
     * Connects to an endpoint and returns the product detail
     * @param token the authorization token
     * @param productId product identifier
     * @param countryId country identifier
     * @param brandId brand identifier
     * @param flowId flow identifier
     * @param storeId store identifier
     * @param platformId platform identifier
     *
     * @return a response http with the product detail
     */
    @GET("v1/menu-hall-products/{id}")
    suspend fun getProductDetail(
            @Header("Authorization") token: String,
            @Path("id") productId: Long,
            @Query("country_id") countryId: Long,
            @Query("brand_id") brandId: Long,
            @Query("flow_id") flowId: Long,
            @Query("store_id") storeId: Long,
            @Query("platform_id") platformId: Long?
    ): Response<APIResponseEntity<MenuHallProductResponseEntity>>

    /**
     * Connects to an endpoint and returns the suggested portions
     * of the portions list given
     *
     * @param token the authorization token
     * @param portionIdList list with the portions identifier identifier
     */
    @GET("v1/portions/changes")
    suspend fun getSuggestedPortions(
            @Header("Authorization") token: String,
            @Query("portion_ids") portionIdList: List<Long>
    ): Response<APIResponseEntity<List<MenuSuggestedPortionResponseEntity>>>
}