package com.robinfood.ordereports_bc_muyapp.usecases.getorderpaymentbyorderids;

import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IGetOrderPaymentByOrderIdUseCase {

    /**
     * Retrieves the list of order payments DTO
     *
     * @param orderId to consult
     *
     * @return {@link List<OrderPaymentDTO>} the list of order payments DTO
     */
    CompletableFuture<List<OrderPaymentDTO>> invoke(Integer orderId);

}
