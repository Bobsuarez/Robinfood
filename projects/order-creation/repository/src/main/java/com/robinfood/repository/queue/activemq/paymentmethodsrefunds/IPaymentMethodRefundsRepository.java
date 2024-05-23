package com.robinfood.repository.queue.activemq.paymentmethodsrefunds;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodRefundResponseDTO;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;

import java.util.concurrent.CompletableFuture;

/**
 * Repository for the connection with the payment queue
 */
public interface IPaymentMethodRefundsRepository {

    /**
     * Sends payment method refund message
     */
    CompletableFuture<PaymentMethodRefundResponseDTO> sendRefundMessage(String token,
                                                        PaymentMethodRefundEntity paymentMethodRefundEntity);
}
