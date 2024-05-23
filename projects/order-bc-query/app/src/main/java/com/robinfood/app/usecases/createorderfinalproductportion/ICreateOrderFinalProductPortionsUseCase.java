package com.robinfood.app.usecases.createorderfinalproductportion;

import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that stores the order final product portions
 */
public interface ICreateOrderFinalProductPortionsUseCase {

    /**
     * Store and retrieves the stored created order final product portions
     * @param portions portions list of the orders
     * @return the stored created order final products
     */
    CompletableFuture<Boolean> invoke(List<FinalProductPortionDTO> portions);
}
