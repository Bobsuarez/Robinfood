package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.posrelatedtoastore.StorePosEntity
import com.robinfood.core.entities.store.StoreConfigurationsEntity
import com.robinfood.core.entities.userconfiguration.UserConfigurationEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Interface provided to make menu orchestrator api calls using Retrofit
 */
interface StoreOrAPI {

    @GET("v1/user/{userId}/pos-configuration")
    suspend fun getUserPosConfiguration(
        @Header("Authorization") token: String,
        @Path("userId") userId: Long
    ): Response<APIResponseEntity<UserConfigurationEntity>>

    @GET("v1/store/{storeId}/configuration")
    suspend fun getStoreConfiguration(
        @Header("Authorization") token: String,
        @Path("storeId") storeId: Long
    ): Response<APIResponseEntity<StoreConfigurationsEntity>>

    @GET("v1/store/{storeId}/pos")
    suspend fun getPosByStoreConfiguration(
        @Header("Authorization") token: String,
        @Path("storeId") storeId: Long
    ): Response<APIResponseEntity<List<StorePosEntity>>>

}