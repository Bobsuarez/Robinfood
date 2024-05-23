package com.robinfood.ordereports_bc_muyapp.usecases.getorderproducttaxesbyfinalproductid;

import com.robinfood.ordereports_bc_muyapp.dto.OrderProductTaxDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case retrieves the list of order product taxes DTO
 */
public interface IGetOrderProductTaxesByFinalProductIdUseCase {

    /**
     * Retrieves the list of order product taxes DTO
     *
     * @param finalProductId the list order products id's DTO
     *
     * @return List <OrderProductTaxDTO> the list of order product taxes DTO
     */
    CompletableFuture<List<OrderProductTaxDTO>> invoke(List<Long> finalProductId);
}
