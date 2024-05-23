package com.robinfood.app.usecases.createorderproducttaxes;

import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that creates taxes for products
 */
public interface ICreateOrderProductTaxesUseCase {

    /**
     * Save tax of the products
     * @param orderRequestFinalProductTaxDTOList DTO List of taxes of products
     * @return the structure of creating a product tax the DTO
     */
    CompletableFuture<Boolean> invoke(List<FinalProductTaxDTO> orderRequestFinalProductTaxDTOList);
}
