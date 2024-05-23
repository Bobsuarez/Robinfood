package com.robinfood.repository.queue.activemq.paymentmethodsrefunds;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.PaymentMethodsBCApi;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class PaymentMethodRefundsDatasource implements IPaymentMethodRefundsDatasource {

    private final PaymentMethodsBCApi paymentMethodsBCApi;

    public PaymentMethodRefundsDatasource(PaymentMethodsBCApi paymentMethodsBCApi) {
        this.paymentMethodsBCApi = paymentMethodsBCApi;
    }

    @Override
    public CompletableFuture<ApiResponseEntity<PaymentMethodRefundResponseEntity>>
    sendRefundMessage(String token, PaymentMethodRefundEntity paymentMethodRefundEntity) {
        log.info("sendRefundMessage paymentMethodRefundEntity {} ", paymentMethodRefundEntity);
        final Result<ApiResponseEntity<PaymentMethodRefundResponseEntity>> paymentMethodRefundResult =
             NetworkExtensionsKt
                        .safeAPICall(paymentMethodsBCApi.refundMessages(
                                token,
                                paymentMethodRefundEntity)
                        );
        log.info("response {} ", paymentMethodRefundResult);
        if (paymentMethodRefundResult instanceof Result.Error) {

            final Throwable messageError = ((Result.Error) paymentMethodRefundResult).getException();

            log.error("Payment Method Refunds Error Message: {}" + messageError.getMessage());

            return CompletableFuture.failedFuture(((Result.Error) paymentMethodRefundResult).getException());
        }

        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<PaymentMethodRefundResponseEntity>>) paymentMethodRefundResult)
                        .getData()
        );
    }
}
