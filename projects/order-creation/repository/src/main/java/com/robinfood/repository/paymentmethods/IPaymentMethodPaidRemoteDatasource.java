package com.robinfood.repository.paymentmethods;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidRequestEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidResponseEntity;

import java.util.concurrent.CompletableFuture;

/**
 * Datasource that handles remote related
 * payment method transactions
 */
public interface IPaymentMethodPaidRemoteDatasource {

    /**
     * Consume service transaction payment method
     * into Payment Method Business Capability
     *
     * @param token -> token the authorization token
     * @param paymentMethodRequest -> Service request
     *
     * @return -> A future containing the result of the operation
     */
    CompletableFuture<ApiResponseEntity<PaymentMethodPaidResponseEntity>> buildPaymentMethod(
        String token,
        PaymentMethodPaidRequestEntity paymentMethodRequest
    );
}
