package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.ExistsTransactionUuidOrderUuidEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.core.entities.changestatusordersrequestentities.ChangeStatusOrdersRequestEntity;
import com.robinfood.core.entities.getordersbytransaction.OrdersByTransactionEntity;
import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionCreationResponseEntity;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelResponseRest;
import com.robinfood.core.models.retrofit.order.pickuptime.PickupTimeModelRest;
import java.time.LocalDate;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Connections to Order Business Capability
 */
public interface OrderBCApi {

    /**
     * Connects to an endpoint in orders business capability to create transactions
     *
     * @param token              the authorization token
     * @param transactionRequest Request body with transaction data
     *
     * @return the transaction creation result
     */
    @POST("v1/orders")
    Call<ApiResponseEntity<TransactionCreationResponseEntity>> createTransaction(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body TransactionRequestEntity transactionRequest
    );

    /**
     * Connects to an endpoint in orders business capability to change status
     *
     * @param changeStatusOrdersRequestEntity Request body with change status orders data
     * @param token                           the authorization token
     *
     * @return the change status result
     */
    @POST("v1/orders/update/status")
    Call<ApiResponseEntity> changeStatus(
            @Body ChangeStatusOrdersRequestEntity changeStatusOrdersRequestEntity,
            @Header(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an endpoint in orders business capability to change status
     *
     * @param changeStateOrderRequestEntity Request body with change status orders data
     * @param token                           the authorization token
     * @return the change status result
     */
    @POST("v1/states")
    Call<ApiResponseEntity<ChangeStateOrderRespondEntity>> changeState(
            @Body ChangeStateOrderRequestEntity changeStateOrderRequestEntity,
            @Header(AUTHORIZATION_HEADER_KEY) String token
    );

    /**
     * Connects to an order-bc endpoint to save the order pickup-time
     *
     * @param token the authorization date
     * @param pickupTimeModelRest request
     *
     * @return response
     */
    @POST("v1/orders/pickup-time")
    Call<ApiResponseEntity<List<PickupTimeModelResponseRest>>> savePickupTime(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body PickupTimeModelRest pickupTimeModelRest
    );

    /**
     * Connects to an endpoint and checks if the user has applied consumption discount on a certain
     * date
     *
     * @param token     the authorization date
     * @param todayDate the date the user wants to check
     * @param userId    the id of the user
     *
     * @return true if the user has applied the consumption discount in a certain date, false
     *         otherwise
     */
    @GET("v1/users/{id}/has-applied-consumption-today")
    Call<ApiResponseEntity<Boolean>> hasUserAppliedConsumptionByDate(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Path("id") Long userId,
            @Query("createdAt") LocalDate todayDate
    );

    /**
     * Connects to an endpoint in orders business capability to find orders by transaction id
     *
     * @param token         the authorization date
     * @param transactionId the id of the transaction
     *
     * @return the orders associated with the transaction
     */
    @GET("v1/orders/{id}/order-by-transaction-id")
    Call<ApiResponseEntity<List<OrdersByTransactionEntity>>> getOrdersByTransactionId(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Path("id") Long transactionId
    );

    /**
     * Connects to an endpoint in orders business capability to validate if uuid exits
     *
     * @param token           for authorization
     * @param orderUuids      list of orders uuid
     * @param transactionUuid Transaction uuid
     *
     * @return the result of de validation
     */
    @GET("v1/orders/exits")
    Call<ApiResponseEntity<ExistsTransactionUuidOrderUuidEntity>> getExitsTransactionUuidOrderUuids(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Query("transactionUuid") String transactionUuid,
            @Query("orderUuids") List<String> orderUuids
    );
}
