package com.robinfood.app.usecases.createorderpayments;

import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that gets the create order payment
 */
public interface ICreateOrderPaymentUseCase {

    /**
     * Retrieves the list of order payments
     * @param paymentMethodDTOList the list of order payment methods for distribute in the each order
     * @param responseCreatedOrderDTOList created order list
     * @return the structure of creating an order payment DTOs
     */
    CompletableFuture<Boolean> invoke(
            List<RequestPaymentMethodDTO> paymentMethodDTOList,
            List<ResponseCreatedOrderDTO> responseCreatedOrderDTOList
    );
}
