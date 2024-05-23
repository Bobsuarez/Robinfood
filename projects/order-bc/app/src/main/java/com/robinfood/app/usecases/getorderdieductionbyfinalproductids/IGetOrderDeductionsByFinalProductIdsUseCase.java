package com.robinfood.app.usecases.getorderdieductionbyfinalproductids;

import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import java.util.List;

/**
 * Use case retrieves the list of order deduction final proudcts DTO
 */
public interface IGetOrderDeductionsByFinalProductIdsUseCase {

    /**
     * Retrieves the list of order deductions DTO by final product id's
     *
     * @param finalProductIds the list order id's
     * @return List <OrderDeductionFinalProductDTO> the list of order deductions by order final products id's
     */
    List<OrderDeductionFinalProductDTO> invoke(List<Long> finalProductIds);
}
