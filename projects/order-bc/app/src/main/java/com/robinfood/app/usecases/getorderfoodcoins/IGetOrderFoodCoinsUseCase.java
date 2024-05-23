package com.robinfood.app.usecases.getorderfoodcoins;

import java.math.BigDecimal;

public interface IGetOrderFoodCoinsUseCase {
    /**
     * Get foodcoins of the transaction
     *
     * @param orderId  id of the orders created
     *
     * @return CompletableFuture<Boolean>
     */
    BigDecimal invoke (Long orderId);
}
