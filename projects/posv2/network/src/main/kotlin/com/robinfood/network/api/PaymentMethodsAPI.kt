package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.paymentmethods.PaymentMethodResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Interface provided to make payment methods api calls using Retrofit
 */
interface PaymentMethodsAPI {

    @GET("v1/payment-methods")
    suspend fun getPaymentMethods(
            @Query("channel_id") channelId: Long,
            @Query("origin_id") originId: Long,
            @Query("store_id") storeId: Long,
            @Header("Authorization") token: String
    ): Response<APIResponseEntity<List<PaymentMethodResponseEntity>>>
}
