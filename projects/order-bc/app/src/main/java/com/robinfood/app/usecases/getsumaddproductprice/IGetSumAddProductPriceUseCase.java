package com.robinfood.app.usecases.getsumaddproductprice;

import com.robinfood.core.dtos.request.order.FinalProductDTO;

import java.util.List;

/**
 * Use case that gets sum of the final products additional portions
 */
public interface IGetSumAddProductPriceUseCase {

    /**
     * Retrieves the additional final products price
     * @param finalProductDTOS list of final products
     * @return additional final products price
     */
    Double invoke(List<FinalProductDTO> finalProductDTOS);
}
