package com.robinfood.app.usecases.createorderflagcorporate;

import com.robinfood.core.dtos.request.order.OrderFlagCorporateDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order flag corporate
 */
public interface ICreateOrderFlagCorporateUseCase {

    /**
     * Retrieves the order flag corporate
     * @param orderFlagCorporateDTOList order flag corporate list
     * @return the structure of creating an order flag corporate DTO
     */
    CompletableFuture<Boolean> invoke(List<OrderFlagCorporateDTO> orderFlagCorporateDTOList);
}
