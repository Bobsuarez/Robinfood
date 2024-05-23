package com.robinfood.repository.paymentmethods;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidRequestEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodPaidResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.PaymentMethodsBCApi;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class PaymentMethodPaidRemoteDatasource implements IPaymentMethodPaidRemoteDatasource {

    private final PaymentMethodsBCApi paymentMethodsBCApi;

    public PaymentMethodPaidRemoteDatasource(PaymentMethodsBCApi paymentMethodsBCApi) {
        this.paymentMethodsBCApi = paymentMethodsBCApi;
    }

    @Override
    public CompletableFuture<ApiResponseEntity<PaymentMethodPaidResponseEntity>> buildPaymentMethod(
            String token,
            PaymentMethodPaidRequestEntity paymentMethodRequestEntity
    ) {

        log.info("Starting to Payment Method Paid Datasource");

        final Result<ApiResponseEntity<PaymentMethodPaidResponseEntity>> paymentMethodResult =
                NetworkExtensionsKt
                        .safeAPICall(paymentMethodsBCApi.createTransaction(
                                token,
                                paymentMethodRequestEntity)
                        );

        if (paymentMethodResult instanceof Result.Error) {

            final Throwable messageError = ((Result.Error) paymentMethodResult).getException();

            log.error("Payment Method Paid Error Message: {}" + messageError.getMessage());

            return CompletableFuture.failedFuture(((Result.Error) paymentMethodResult).getException());
        }

        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<PaymentMethodPaidResponseEntity>>) paymentMethodResult)
                        .getData()
        );
    }
}
