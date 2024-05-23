package com.robinfood.app.usecases.getorderproducttaxesbyfinalproductids;

import com.robinfood.core.dtos.OrderProductTaxDTO;

import java.util.List;

/**
 * Use case retrieves the list of order product taxes DTO
 */
public interface IGetOrderProductTaxesByFinalProductIdsUseCase {

    /**
     * Retrieves the list of order product taxes DTO
     *
     * @param finalProductIds the list order products id's DTO
     * @return List <OrderProductTaxDTO> the list of order product taxes DTO
     */
    List<OrderProductTaxDTO> invoke(List<Long> finalProductIds);
}
