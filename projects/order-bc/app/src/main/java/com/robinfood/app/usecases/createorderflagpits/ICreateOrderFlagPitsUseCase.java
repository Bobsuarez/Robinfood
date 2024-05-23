package com.robinfood.app.usecases.createorderflagpits;

import com.robinfood.core.dtos.request.order.OrderFlagPitsDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order flag pits
 */
public interface ICreateOrderFlagPitsUseCase {

    /**
     * Retrieves the order flag pits
     * @param orderFlagPitsDTOList order flag pits list
     * @return the structure of creating an order flag pits DTO
     */
    CompletableFuture<List<OrderFlagPitsDTO>> invoke(List<OrderFlagPitsDTO> orderFlagPitsDTOList);
}
