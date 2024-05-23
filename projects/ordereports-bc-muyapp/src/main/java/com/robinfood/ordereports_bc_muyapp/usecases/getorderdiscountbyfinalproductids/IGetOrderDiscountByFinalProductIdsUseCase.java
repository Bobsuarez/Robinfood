package com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyfinalproductids;

import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case retrieves the list of order discounts DTO
 */
public interface IGetOrderDiscountByFinalProductIdsUseCase {

    /**
     * Retrieves the list of order discounts DTO by final product id's
     *
     * @param finalProductIds the list order id's
     *
     * @return List <OrderDiscountDTO> the list of order discount by products id's
     */
    CompletableFuture<List<OrderDiscountDTO>> invoke(List<Long> finalProductIds);
}
