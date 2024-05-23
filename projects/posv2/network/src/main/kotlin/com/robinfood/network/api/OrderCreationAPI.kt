package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.TransactionCreatedResponseEntity
import com.robinfood.core.entities.transactionrequest.TransactionRequestEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderCreationAPI {

    @POST("v1/transaction")
    suspend fun createTransaction(
        @Header("Authorization") token: String,
        @Body transactionRequestEntity: TransactionRequestEntity
    ): Response<APIResponseEntity<TransactionCreatedResponseEntity>>
}