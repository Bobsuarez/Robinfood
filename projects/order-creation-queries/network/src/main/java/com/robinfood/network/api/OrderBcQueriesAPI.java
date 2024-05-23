package com.robinfood.network.api;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderTotalDailySalesResponseDTO;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.dtos.ordersstore.OrderStoreDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;
import com.robinfood.core.dtos.salesstore.SalesStoresDTO;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDailySaleSummaryEntity;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.core.entities.PaymentMethodsEntity;
import com.robinfood.core.entities.orderhistory.response.OrderHistoryItemEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Defines all connections to Order Business Capability Read Only
 */
@Component
public interface OrderBcQueriesAPI {

    /**
     * return list with orders total daily sales
     *
     * @param token     token with autorization
     * @param storeId   id store
     * @param createdAt date per consulting data
     * @return an object of Order daily sales summary
     * @author Jose Mario Londoño J.- CKS
     */
    @GET("v1/orders/stores/{storeId}/report/daily-sale-summary")
    Call<APIResponseEntity<OrderDailySaleSummaryEntity>> getOrderDailySalesSummary(
            @Header("Authorization") String token,
            @Path("storeId") Long storeId,
            @Query("createdAt") LocalDate createdAt
    );

    /**
     * Get Order History Filter
     *
     * @param channelsId     Filter by Id from the channels.
     * @param currentPage    Current Page of Records.
     * @param isDelivery     Orders Delivery filter.
     * @param localDateEnd   End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @param perPage        Records by pages.
     * @param searchText     Filter text for records by (orderInvoiceNumber, Brand and User Name).
     * @param storeId        Number to filter by Id from the store.
     * @param timeZone       Geographic region in which the same time is used.
     * @param token          token with authorization.
     * @return Get Order History
     */
    @GET("v1/orders/{storeId}/history")
    Call<APIResponseEntity<EntityDTO<OrderHistoryItemEntity>>> getOrderHistoryByStore(
            @Path("storeId") Long storeId,
            @Query("channelsId") List<Long> channelsId,
            @Query("currentPage") Integer currentPage,
            @Query("isDelivery") Boolean isDelivery,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("localDateStart") LocalDate localDateStart,
            @Query("perPage") Integer perPage,
            @Query("searchText") String searchText,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
    );

    /**
     * return list with orders total daily sales
     *
     * @param token     token with autorization
     * @param storeId   id store
     * @param createdAt date per consulting data
     * @return list Order Total Daily Sales
     */
    @GET("v1/orders/stores/{storeId}/report/total-daily-sales")
    Call<APIResponseEntity<List<OrderTotalDailySalesResponseDTO>>> getOrderTotalDailySales(
            @Header("Authorization") String token,
            @Path("storeId") String storeId,
            @Query("createdAt") LocalDate createdAt
    );

    /**
     * return the object with the resolution and count of effective and canceled invoices
     *
     * @param posId          id the pos
     * @param token          token with authorization
     * @param timeZone       Geographic region in which the same time is used.
     * @param localDateEnd   End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @return Get Pos Resolution
     */
    @GET("v1/orders/pos-resolutions/{posId}/sequence")
    Call<APIResponseEntity<GetPosResolutionsDTO>> getPosResolutionsSequence(
            @Path("posId") Long posId,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("localDateStart") LocalDate localDateStart,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
    );

    /**
     * return the object with the orders payment methods
     *
     * @param storeId        id the store
     * @param token          token with authorization
     * @param timeZone       Geographic region in which the same time is used.
     * @param localDateEnd   End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @return Get list orders payment methods
     */
    @GET("v1/orders/store/{storeId}/orders")
    Call<APIResponseEntity<List<OrderStoreDTO>>> getOrdersStore(
            @Path("storeId") Long storeId,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("localDateStart") LocalDate localDateStart,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
    );

    /**
     * return the object with the orders payment methods
     *
     * @param posId          id the pos
     * @param token          token with authorization
     * @param timeZone       Geographic region in which the same time is used.
     * @param localDateEnd   End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @return Get list orders payment methods
     */
    @GET("v1/orders/orders-payment/{posId}/detail")
    Call<APIResponseEntity<List<OrderPaymentDTO>>> getOrderPaymentMethods(
            @Path("posId") Long posId,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("localDateStart") LocalDate localDateStart,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
    );

    /**
     * returns the list of orders grouped by categories
     *
     * @param posId          id the pos
     * @param token          token with authorization
     * @param timeZone       Geographic region in which the same time is used.
     * @param localDateEnd   End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @return Get categories list
     */
    @GET("v1/orders/{posId}/categories")
    Call<APIResponseEntity<List<OrderCategoryDTO>>> getOrdersGroupedByCategories(
            @Path("posId") Long posId,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("localDateStart") LocalDate localDateStart,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
    );

    /**
     * return the list of objects with the resolution and count of current and canceled invoices
     *
     * @param storeId        id the store to search
     * @param token          token with authorization
     * @param timeZone       Geographic region in which the same time is used.
     * @param localDateEnd   End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @return the list of objects with resolution
     */
    @GET("v1/orders/{storeId}/resolutions-sequence")
    Call<APIResponseEntity<List<GetPosResolutionsDTO>>> getPosResolutionsSequenceByStore(
            @Path("storeId") Long storeId,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("localDateStart") LocalDate localDateStart,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
    );

    /**
     * return the list of objects with the sales store group by payment methods
     *
     * @param storeId         id the store to search
     * @param dateTimeCurrent date to consult the records
     * @param token           token with authorization
     * @param timezone        timezone of a store
     * @return the DTO contain sales stores group by payment methods
     */
    @GET("v1/report/sales/store/{storeId}/payment-methods")
    Call<APIResponseEntity<SalesStoresDTO>> getSalesByStoreGroupByPaymentMethods(
            @Path("storeId") Long storeId,
            @Query("dateTimeCurrent") LocalDateTime dateTimeCurrent,
            @Query("timezone") String timezone,
            @Header("Authorization") String token
    );

    /**
     * return the list of active sales with the orders by companies
     *
     * @param companies       list id companies
     * @param dateTimeCurrent current date time to consult the records
     * @param token           token with authorization
     * @param timezones       timezones per companies
     * @return the list of active sales with orders by companies
     */
    @GET("v1/report/sales/companies/orders")
    Call<APIResponseEntity<ResponseOrderActiveSalesDTO>> getActiveSalesToOrderByCompanies(
            @Query("companies") List<Integer> companies,
            @Query("dateTimeCurrent") String dateTimeCurrent,
            @Query("timezones") List<String> timezones,
            @Header("Authorization") String token
    );

    /**
     * return a list of order detail entity print
     *
     * @param token     token with authorization
     * @param orderIds  list of order ids
     * @param orderUids list of order uids
     * @param orderUuid list of order uuids
     * @return list of order detail entity print
     */
    @GET("v1/orders/detail/print")
    Call<APIResponseEntity<List<OrderDetailEntity>>> getOrderDetailPrint(
            @Header("Authorization") String token,
            @Query("orderIds") List<Long> orderIds,
            @Query("orderUids") List<String> orderUids,
            @Query("orderUuid") List<String> orderUuid
    );

    /**
     * return the list of payment method objects
     *
     * @param token token with authorization
     * @return the list of objects payment method
     */
    @GET("v1/payment-methods")
    Call<APIResponseEntity<List<PaymentMethodsEntity>>> getPaymentMethodsList(
            @Header("Authorization") String token
    );

    /**
     * Get the list orders paid by filters
     *
     * @param brandIds          ids of the brands to search
     * @param companyId         id of the company to search
     * @param idCustomFilter    id of the custom filter to search
     * @param localDateStart    Start date to consult the records
     * @param localDateEnd      End date to consult the records
     * @param originIds         ids of the origin to search
     * @param paymentMethodIds  ids of the payment methods to search
     * @param statusCode        code of the status order to search
     * @param storeIds          ids of the store to search
     * @param valueCustomFilter value of the custom filter to search
     * @param currentPage       current page to list
     * @param perPage           per page to list
     * @param token             token with authorization
     * @param timezone          timezones of the company
     * @return the object GetOrdersPaidDTO
     */
    @GET("/api/v1/paid-orders")
    Call<APIResponseEntity<GetOrdersPaidDTO>> getPaidOrders(
            @Query("brandIds") List<Long> brandIds,
            @Query("companyId") Long companyId,
            @Query("currentPage") Integer currentPage,
            @Query("idCustomFilter") Long idCustomFilter,
            @Query("localDateStart") LocalDate localDateStart,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("originIds") List<Long> originIds,
            @Query("paymentMethodIds") List<Long> paymentMethodIds,
            @Query("perPage") Integer perPage,
            @Query("statusCode") String statusCode,
            @Query("storeIds") List<Long> storeIds,
            @Header("timeZone") String timezone,
            @Query("valueCustomFilter") String valueCustomFilter,
            @Header("Authorization") String token
    );

    /**
     * Get the information from the sales report by filters - channels - brands - stores - paymentMethods
     *
     * @param brands          list of the brands to search
     * @param companies       list of the companies to search
     * @param channels        list of the channels to search
     * @param dateTimeCurrent End date to consult the records
     * @param paymentMethods  list of the paymentMethods to search
     * @param stores          list of the stores to search
     * @param token           token with authorization
     * @param timezones       timezones of the companies
     * @return the object SaleBySegmentResponseDTO
     */
    @GET("v1/report/sales")
    Call<APIResponseEntity<GetSaleBySegmentDTO>> getSalesSegment(
            @Query("brands") List<Long> brands,
            @Query("companies") List<Long> companies,
            @Query("channels") List<Long> channels,
            @Query("dateTimeCurrent") LocalDateTime dateTimeCurrent,
            @Query("paymentMethods") List<Long> paymentMethods,
            @Query("stores") List<Long> stores,
            @Query("timezones") List<String> timezones,
            @Header("Authorization") String token
    );

    /**
     * Get the information status order history
     *
     * @param token token with authorization
     * @param uuid  the order to search
     * @return the object StatusOrderHistoryDTO
     */
    @GET("v1/orders/{uuid}/status-history")
    Call<APIResponseEntity<List<StatusOrderHistoryDTO>>> getStatusOrderHistory(
            @Header("Authorization") String token,
            @Path("uuid") String uuid
    );

    /**
     * return a list of order detail entity
     *
     * @param token     token with authorization
     * @param orderIds  list of order ids
     * @param orderUids list of order uids
     * @param orderUuid list of order uuids
     *
     * @return list of order detail entity
     */
    @GET("v1/orders")
    Call<APIResponseEntity<List<OrderDetailEntity>>> getOrderDetail(
            @Header("Authorization") String token,
            @Query("orderIds") List<Long> orderIds,
            @Query("orderUids") List<String> orderUids,
            @Query("orderUuid") List<String> orderUuid
    );
}
