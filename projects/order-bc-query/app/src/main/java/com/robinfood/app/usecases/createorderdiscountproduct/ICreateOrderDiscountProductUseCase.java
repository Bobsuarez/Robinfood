package com.robinfood.app.usecases.createorderdiscountproduct;

import com.robinfood.core.dtos.request.order.OrderDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that gets the creation order discount by product
 */
public interface ICreateOrderDiscountProductUseCase {
    /**
     * Create discounts by product in order-discounts
     *
     * @param orderDTOList the list of order discount for creating an order discount
     * @param orderIds list of order ids
     * @return the structure of creating an order discount DTO
     */
    CompletableFuture<Boolean> invoke(List<OrderDTO> orderDTOList, List<Long> orderIds);
}
