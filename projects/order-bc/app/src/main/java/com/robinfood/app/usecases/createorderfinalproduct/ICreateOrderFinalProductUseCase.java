package com.robinfood.app.usecases.createorderfinalproduct;

import com.robinfood.core.dtos.request.order.FinalProductDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case that stores the order_final_product and its (added, changed, removed) portions
 */
public interface ICreateOrderFinalProductUseCase {

    /**
     * Store and retrieves the created final products and creates its (added, changed, removed) portions
     * @param finalProducts product list of the orders
     * @param orderId order id
     * @param companyId company id
     * @param paid indicates if the order is paid
     * @return the recently created final products
     */
    CompletableFuture<Boolean> invoke(
            List<FinalProductDTO> finalProducts,
            Long orderId,
            Long companyId,
            Long storeId,
            Boolean paid
    );
}
