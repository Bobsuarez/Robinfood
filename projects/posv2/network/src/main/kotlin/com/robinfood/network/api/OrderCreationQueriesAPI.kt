package com.robinfood.network.api

import com.robinfood.core.entities.APIResponseEntity
import com.robinfood.core.entities.HistoryEntity
import com.robinfood.core.entities.OrderDetailEntity
import com.robinfood.core.entities.dailyreportvoucher.OrderCategoryEntity
import com.robinfood.core.entities.dailyreportvoucher.OrderGroupPaymentEntity
import com.robinfood.core.entities.orderdaily.OrderDailyEntity
import com.robinfood.core.entities.posresolutionsequence.PosResolutionSequenceEntity
import com.robinfood.core.entities.witnesstape.StoreOrdersEntity
import org.springframework.stereotype.Repository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

@Repository
interface OrderCreationQueriesAPI {

    @GET("v1/orders/detail")
    suspend fun getOrdersDetail(
        @Header("Authorization") token: String,
        @Query("countryId") countryId: Long,
        @Query("flowId") flowId: Long,
        @Query("orderIds") orderIds: List<Long>
    ): Response<APIResponseEntity<List<OrderDetailEntity>>>

    @GET("v1/orders/{storeId}/history")
    suspend fun getOrderHistory(
        @Path("storeId") storeId: Long?,
        @Header("TimeZone") timeZone: String?,
        @Header("Authorization") token: String?,
        @Query("channelsId") channelsId: Int?,
        @Query("currentPage") currentPage: Int?,
        @Query("localDateStart") localDateStart: LocalDate?,
        @Query("localDateEnd") localDateEnd: LocalDate?,
        @Query("perPage") perPage: Int?,
        @Query("isDelivery") isDelivery: Boolean?,
        @Query("searchText") searchText: String?
    ): Response<APIResponseEntity<HistoryEntity>>

    @GET("v1/orders/{storeId}/daily")
    suspend fun getOrdersDaily(
        @Header("Authorization") token: String,
        @Header("timeZone") timeZone: String,
        @Path("storeId") storeId: Long
    ): Response<APIResponseEntity<List<OrderDailyEntity>>>

    @GET("v1/orders/pos-resolutions/{posId}/sequence")
    suspend fun getPosResolutionSequence(
        @Header("Authorization") token: String,
        @Header("timeZone") timeZone: String,
        @Path("posId") posId: Long,
        @Query("localDateStart") localDateStart: LocalDate,
        @Query("localDateEnd") localDateEnd: LocalDate
    ): Response<APIResponseEntity<PosResolutionSequenceEntity>>

    @GET("v1/orders/{posId}/categories")
    suspend fun getOrderGroupCategories(
        @Header("Authorization") token: String,
        @Header("timeZone") timeZone: String,
        @Path("posId") posId: Long,
        @Query("localDateStart") localDateStart: LocalDate,
        @Query("localDateEnd") localDateEnd: LocalDate
    ): Response<APIResponseEntity<List<OrderCategoryEntity>>>

    @GET("v1/orders/orders-payment/{posId}/detail")
    suspend fun getOrderGroupPayments(
        @Header("Authorization") token: String,
        @Header("timeZone") timeZone: String,
        @Path("posId") posId: Long,
        @Query("localDateStart") localDateStart: LocalDate,
        @Query("localDateEnd") localDateEnd: LocalDate
    ): Response<APIResponseEntity<List<OrderGroupPaymentEntity>>>

    @GET("v1/orders/store/{storeId}/orders")
    suspend fun getOrdersByStore(
        @Header("Authorization") token: String,
        @Header("timeZone") timeZone: String,
        @Path("storeId") posId: Long,
        @Query("localDateStart") localDateStart: LocalDate,
        @Query("localDateEnd") localDateEnd: LocalDate
    ): Response<APIResponseEntity<List<StoreOrdersEntity>>>

    @GET("v1/orders/{storeId}/resolutions-sequence")
    suspend fun getStoreResolutionSequence(
        @Header("Authorization") token: String,
        @Header("timeZone") timeZone: String,
        @Path("storeId") storeId: Long,
        @Query("localDateStart") localDateStart: LocalDate,
        @Query("localDateEnd") localDateEnd: LocalDate
    ): Response<APIResponseEntity<List<PosResolutionSequenceEntity>>>
}