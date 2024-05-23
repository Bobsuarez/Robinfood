package com.robinfood.app.usecases.createorderintegrations;

import com.robinfood.core.dtos.request.order.OrderIntegrationDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that gets the create order integration
 */
public interface ICreateOrderIntegrationUseCase {

    /**
     * Retrieves the order integration
     * @param orderIntegrationDTOList for creating an order integration
     * @return the structure of creating an order integration DTO
     */
    CompletableFuture<Boolean> invoke(List<OrderIntegrationDTO> orderIntegrationDTOList);
}
