package com.robinfood.app.usecases.getsumdiscountprice;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import java.util.List;

/**
 * Use case that gets sum of the order discounts
 */
public interface IGetSumDiscountPriceUseCase {

    /**
     * Retrieves the order discount price
     * @param finalProductDTOS final product list
     * @return the sum of order discounts
     */
    Double invoke(List<FinalProductDTO> finalProductDTOS);

}
