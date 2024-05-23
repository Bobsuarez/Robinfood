package com.robinfood.app.usecases.createpayments;

import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order flag togo
 */
public interface ICreatePaymentsUseCase {

    /**
     * Retrieves the order flag togo
     * @param paymentMethodDTOList for creating transaction payments
     * @return the structure of creating an payment DTO
     */
    CompletableFuture<Boolean> invoke(List<RequestPaymentMethodDTO> paymentMethodDTOList, Long transactionId);
}
