package com.robinfood.repository.paymentmethods;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidRequestDTO;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidResponseDTO;

import java.util.concurrent.CompletableFuture;

/**
 * Repository that handles payment methods
 * from orders processing
 */
public interface IPaymentMethodPaidRepository {

    /**
     * Consume service transaction into
     * Payment Methods Business capability
     * and manages the paid with the payment
     * method selected in the order transaction
     *
     * @param token -> The authorization token
     * @param paymentMethodPaidRequestDTO ->The request transaction
     *
     * @return -> A future containing the result of the transaction
     */
    CompletableFuture<PaymentMethodPaidResponseDTO> send(
        String token,
        PaymentMethodPaidRequestDTO paymentMethodPaidRequestDTO
    );
}
