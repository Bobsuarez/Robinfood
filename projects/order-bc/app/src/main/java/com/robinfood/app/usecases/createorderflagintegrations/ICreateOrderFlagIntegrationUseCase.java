package com.robinfood.app.usecases.createorderflagintegrations;

import com.robinfood.core.dtos.request.order.OrderFlagIntegrationDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that gets the create order flag integration
 */
public interface ICreateOrderFlagIntegrationUseCase {

    /**
     * Retrieves the order flag integration
     * @param orderFlagIntegrationDTOList order flag integration list
     * @return the structure of creating an order flag integration DTO
     */
    CompletableFuture<Boolean> invoke(List<OrderFlagIntegrationDTO> orderFlagIntegrationDTOList);
}
