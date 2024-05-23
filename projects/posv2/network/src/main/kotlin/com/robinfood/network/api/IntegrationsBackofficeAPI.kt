package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.store.StoreDeliveryPlatformResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface provided to make integrations backoffice api calls using Retrofit
 */
interface IntegrationsBackofficeAPI {

    @GET("v1/stores/{id}/platforms")
    suspend fun getStoreDeliveryPlatforms(
            @Path("id") storeId: Long,
            @Query("page") page: Int?,
            @Query("size") size: Int?,
            @Header("Authorization") token: String
    ): Response<APIResponseEntity<StoreDeliveryPlatformResponseEntity>>
}
