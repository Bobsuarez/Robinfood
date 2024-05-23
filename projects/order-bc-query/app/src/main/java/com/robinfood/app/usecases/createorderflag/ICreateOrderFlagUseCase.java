package com.robinfood.app.usecases.createorderflag;

import com.robinfood.core.dtos.request.order.IntermediateOrderFlagDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that gets the create order flag
 */
public interface ICreateOrderFlagUseCase {

    /**
     * Retrieves the order flag
     * @param orderFlagDTO the list of order flags for creating an order flag
     * @return the structure of creating an order flag DTO
     */
    CompletableFuture<Boolean> invoke(List<IntermediateOrderFlagDTO> orderFlagDTO);
}
