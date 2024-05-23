package com.robinfood.app.usecases.createorderflagsubmarine;

import com.robinfood.core.dtos.request.order.OrderFlagSubmarineDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order flag submarine
 */
public interface ICreateOrderFlagSubmarineUseCase {

    /**
     * Retrieves the order flag submarine
     * @param orderFlagSubmarineDTOList for creating an order flag submarine
     * @return the structure of creating an order flag submarine DTO
     */
    CompletableFuture<Boolean> invoke(List<OrderFlagSubmarineDTO> orderFlagSubmarineDTOList);
}
