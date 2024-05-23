package com.robinfood.app.usecases.createorderflagtogo;

import com.robinfood.core.dtos.request.order.OrderFlagTogoDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates the order flag togo
 */
public interface ICreateOrderFlagTogoUseCase {

    /**
     * Retrieves the order flag togo
     * @param orderFlagTogoDTOList order flag togo list
     * @return the structure of creating an order flag togo DTO
     */
    CompletableFuture<Boolean> invoke(List<OrderFlagTogoDTO> orderFlagTogoDTOList);
}
