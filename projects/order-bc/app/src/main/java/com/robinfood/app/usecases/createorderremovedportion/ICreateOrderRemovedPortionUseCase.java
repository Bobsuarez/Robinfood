package com.robinfood.app.usecases.createorderremovedportion;

import com.robinfood.core.dtos.request.order.FinalProductRemovedPortionDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that stores the removed portions of the order
 */
public interface ICreateOrderRemovedPortionUseCase {

    /**
     * Store and retrieves the created changed portions of the order
     * @param removedPortionsDTO removed portions list of the orders
     * @return the recently stored removed portions
     */
    CompletableFuture<Boolean> invoke(List<FinalProductRemovedPortionDTO> removedPortionsDTO);
}
