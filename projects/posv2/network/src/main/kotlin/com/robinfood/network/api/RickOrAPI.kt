package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.customerinvoice.ResultPrintFormatEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Interface provided to make menu orchestrator api calls using Retrofit
 */
interface RickOrAPI {

    @GET("v1/orders/{order_id}/print-format")
    suspend fun getOrderCustomerInvoiceDetail(
        @Header("Authorization") token: String,
        @Header("Origin") origin: Int,
        @Path("order_id") orderId: Long
    ): Response<APIResponseEntity<ResultPrintFormatEntity>>

}