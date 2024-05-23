package com.robinfood.app.usecases.getsumproducttaxesprice;

import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;

import java.util.List;

/**
 * Use case that gets sum of the final product taxes
 */
public interface IGetSumProductTaxPriceUseCase {

    /**
     * Retrieves the final product taxes price
     * @param listTaxesByProductsDTOS final product taxes
     * @return the sum of the order final products taxes
     */
    Double invoke(List<List<FinalProductTaxDTO>> listTaxesByProductsDTOS);
}
