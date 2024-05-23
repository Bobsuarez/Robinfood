package com.robinfood.app.usecases.calculatesummationfinalproductaddition;

import com.robinfood.core.dtos.request.order.FinalProductDTO;

/**
 * Use case calculate summation of the additions in final product
 */
public interface ICalculateFinalProductAdditionUseCase {

    /**
     * Calculate summation of additions of the final product
     * @param finalProductDTO final product of the order
     * @return the summation of additions
     */
    Double invoke(FinalProductDTO finalProductDTO);
}
