package com.robinfood.app.usecases.calculatesummationfinalproductdiscount;

import com.robinfood.core.dtos.request.order.FinalProductDTO;

/**
 * Use case calculate summation of discounts on final product
 */
public interface ICalculateFinalProductDiscountUseCase {

    /**
     * Calculate summation of discounts of the final product
     * @param finalProductDTO final product of the order
     * @return the summation of discounts
     */
    Double invoke(FinalProductDTO finalProductDTO);
}
