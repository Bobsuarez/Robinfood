package com.robinfood.app.usecases.getsumproductsprice;

import com.robinfood.core.dtos.request.order.FinalProductDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Use case that gets sum of the final products
 */
public interface IGetSumProductPriceUseCase {

    /**
     * Retrives final products price
     * @param finalProductDTOList Final porduct list
     * @return the sum of the order final products
     */
    BigDecimal invoke(List<FinalProductDTO> finalProductDTOList);
}
