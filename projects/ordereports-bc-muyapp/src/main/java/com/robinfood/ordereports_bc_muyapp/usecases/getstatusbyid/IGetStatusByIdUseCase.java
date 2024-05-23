package com.robinfood.ordereports_bc_muyapp.usecases.getstatusbyid;

import com.robinfood.ordereports_bc_muyapp.dto.OrderStatusDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case retrieves the get status
 */
public interface IGetStatusByIdUseCase {

    /**
     * Retrieves the status
     *
     * @param statusId status id
     *
     * @return status dto
     */
    CompletableFuture<List<OrderStatusDTO>> invoke(List<Short> statusId);
}
