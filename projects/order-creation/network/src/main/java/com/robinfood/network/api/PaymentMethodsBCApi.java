package com.robinfood.network.api;

import com.robinfood.core.constants.APIConstants;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidRequestEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Connections to Payment Methods Business Capability
 */
public interface PaymentMethodsBCApi {

    /**
     * Connects to an endpoint in Payment Methods Business Capability
     * to create transaction with the payment method selected in
     * order created
     *
     * @param token -> Authorization token
     * @param paymentMethodRequest -> Request body with transaction data
     *
     * @return Transaction object result from Payment Methods BC
     */
    @POST("v1/transactions")
    Call<ApiResponseEntity<PaymentMethodPaidResponseEntity>> createTransaction(
            @Header(APIConstants.AUTHORIZATION_HEADER_KEY) String token,
            @Body PaymentMethodPaidRequestEntity paymentMethodRequest
    );

    /**
     * Method responsible for communicating a rejection status through http
     *
     * @param token -> Authorization token
     * @param paymentMethodRequest -> Request body with transaction data
     *
     * @return Payment method refund response
     */
    @POST("v1/transactions/messages/refund")
    Call<ApiResponseEntity<PaymentMethodRefundResponseEntity>> refundMessages(
            @Header(APIConstants.AUTHORIZATION_HEADER_KEY) String token,
            @Body PaymentMethodRefundEntity paymentMethodRequest
    );
}
