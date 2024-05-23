package com.robinfood.app.usecases.getorderdiscountbyorderids;

import com.robinfood.core.dtos.OrderDiscountDTO;

import java.util.List;

/**
 * Use case retrieves the list of order discounts DTO
 */
public interface IGetOrderDiscountByOrderIdsUseCase {

    /**
     * Retrieves the list of order discounts DTO
     *
     * @param orderIds the list order id's DTO
     * @return List <OrderDiscountDTO> the list of order discount by orders DTO
     */
    List<OrderDiscountDTO> invoke(List<Long> orderIds);
}
