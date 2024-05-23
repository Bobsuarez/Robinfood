package com.robinfood.app.usecases.calculatesummationfinalproducttax;

import com.robinfood.core.dtos.request.order.FinalProductDTO;

/**
 * Use case calculate summation of taxes on final product
 */
public interface ICalculateSummationFinalProductTaxUseCase {

    /**
     * Calculate summation of taxes of the final product
     * @param finalProductDTO final products of the order
     * @return the summation of taxes
     */
    Double invoke(FinalProductDTO finalProductDTO);
}
