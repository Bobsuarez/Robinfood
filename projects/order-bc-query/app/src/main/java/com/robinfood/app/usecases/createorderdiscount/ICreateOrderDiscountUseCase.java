package com.robinfood.app.usecases.createorderdiscount;

import com.robinfood.core.dtos.request.order.OrderDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that gets the create order discount
 */
public interface ICreateOrderDiscountUseCase {

    /**
     * Retrieves the order flag
     * @param orderDiscountDTO the list of order discount for creating an order discount
     * @return the structure of creating an order discount DTO
     */
    CompletableFuture<Boolean> invoke(List<OrderDTO> orderDiscountDTO, List<Long> orderIds);
}
