package com.robinfood.app.usecases.getorderdiscountbyfinalproductids;

import com.robinfood.core.dtos.OrderDiscountDTO;

import java.util.List;

/**
 * Use case retrieves the list of order discounts DTO
 */
public interface IGetOrderDiscountByFinalProductIdsUseCase {

    /**
     * Retrieves the list of order discounts DTO by final product id's
     *
     * @param finalProductIds the list order id's
     * @return List <OrderDiscountDTO> the list of order discount by products id's
     */
    List<OrderDiscountDTO> invoke(List<Long> finalProductIds);
}
