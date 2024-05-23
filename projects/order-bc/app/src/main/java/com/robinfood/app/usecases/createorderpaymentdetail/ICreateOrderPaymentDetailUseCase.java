package com.robinfood.app.usecases.createorderpaymentdetail;

import com.robinfood.core.dtos.request.transaction.RequestPaymentDetailDTO;

import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order payment detail
 */
public interface ICreateOrderPaymentDetailUseCase {

    /**
     * Save the details of the payment methods
     * @param orderPaymentDetailDTO DTO List of details of payment methods
     * @return the structure of creating an order payment detail DTO
     */
    CompletableFuture<Boolean> invoke(RequestPaymentDetailDTO orderPaymentDetailDTO);
}
