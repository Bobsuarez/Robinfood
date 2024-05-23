package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.FailedTransaction;
import com.robinfood.paymentmethodsbc.model.FailedTransactionType;
import java.time.LocalDateTime;

public final class FailedTransactionsSample {

    private FailedTransactionsSample() {}

    public static FailedTransactionType getFailedTransactionType() {
        return FailedTransactionType
            .builder()
            .id(1L)
            .name("Internal")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static FailedTransaction getFailedTransaction() {
        return FailedTransaction
            .builder()
            .id(1L)
            .transaction(TransactionSamples.getTransaction())
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .paymentMethod(GeneralPaymentMethodSample.getGeneralPaymentMethod())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
