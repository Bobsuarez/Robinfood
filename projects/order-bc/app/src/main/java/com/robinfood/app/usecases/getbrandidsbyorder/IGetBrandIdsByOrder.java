package com.robinfood.app.usecases.getbrandidsbyorder;

import com.robinfood.core.dtos.request.order.FinalProductDTO;

import java.util.List;

/**
 * Use case that returns the ids of the branches by products
 */
public interface IGetBrandIdsByOrder {

    /**
     * Returns the ids of the brands by products
     *
     * @param finalProductDTOList List final Product DTO
     *
     * @return List ids brands
     */
    List<Long> invoke(List<FinalProductDTO> finalProductDTOList);
}
