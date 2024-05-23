package com.robinfood.network.api;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderDailyEntity;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.core.entities.OrderEntity;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Defines all connections to Order Business Capability
 */
@Component
public interface OrderBcAPI {

    /**
     * return a list of order daily entity
     *
     * @param storeId Store identifier
     * @param timeZone Client time zone
     * @param token token with authorization
     * @return List Order Daily Entity
     */
    @GET("v1/orders/{storeId}/daily")
    Call<APIResponseEntity<List<OrderDailyEntity>>> getOrderDaily(
            @Path("storeId") Long storeId,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
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

    /**
     * return a list of order detail entity print
     *
     * @param token     token with authorization
     * @param orderIds  list of order ids
     * @param orderUids list of order uids
     * @param orderUuid list of order uuids
     *
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
     * Return List Order detail filter order information by different fields
     *
     * @param currentPage Current Page of Records.
     * @param filterText Filter text for records by (orderNumber and orderInvoiceNumber).
     * @param localDateEnd End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @param perPage Records by pages.
     * @param storeId Number to filter by Id from the store.
     * @param timeZone Geographic region in which the same time is used.
     * @param token token with authorization.
     * @return List Call order filter
     */
    @GET("v1/orders/filter")
    Call<APIResponseEntity<EntityDTO<OrderEntity>>> getOrderFilter(
            @Query("currentPage") Integer currentPage,
            @Query("filterText") String filterText,
            @Query("localDateEnd") LocalDate localDateEnd,
            @Query("localDateStart") LocalDate localDateStart,
            @Query("perPage") Integer perPage,
            @Query("storeId") Long storeId,
            @Header("timeZone") String timeZone,
            @Header("Authorization") String token
    );

    /**
     * return list with orders by user
     *
     * @param currentPage current page
     * @param perPage     results for page
     * @param token       token with autorization
     * @param userId      id user
     * @return list Order
     */
    @GET("v1/users/{id}/orders")
    Call<APIResponseEntity<EntityDTO<ResponseOrderDTO>>> getOrderHistoryByUser(
        @Header("Authorization") String token,
        @Path("id") Long userId,
        @Query("currentPage") Integer currentPage,
        @Query("perPage") Integer perPage
    );

    /**
     * return detail order by user
     *
     * @param orderUId uid order
     * @param token    token with autorization
     * @param userId   id user
     * @return the detail order
     */
    @GET("v1/users/{id}/orders/{orderUId}")
    Call<APIResponseEntity<ResponseOrderDetailDTO>> getOrderDetailByUser(
        @Header("Authorization") String token,
        @Path("id") Long userId,
        @Path("orderUId") String orderUId
    );

    /**
     * return list with active orders by user
     *
     * @param token  token with autorization
     * @param userId id user
     * @return list Order
     */
    @GET("v1/users/{id}/orders/active")
    Call<APIResponseEntity<List<ResponseOrderDTO>>> getActiveOrdersByUser(
        @Header("Authorization") String token,
        @Path("id") Long userId
    );

}
