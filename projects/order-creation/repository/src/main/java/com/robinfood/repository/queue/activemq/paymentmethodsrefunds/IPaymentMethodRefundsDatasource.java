package com.robinfood.repository.queue.activemq.paymentmethodsrefunds;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IPaymentMethodRefundsDatasource {

    CompletableFuture<ApiResponseEntity<PaymentMethodRefundResponseEntity>>
    sendRefundMessage(String token, PaymentMethodRefundEntity paymentMethodRefundEntity);

}
