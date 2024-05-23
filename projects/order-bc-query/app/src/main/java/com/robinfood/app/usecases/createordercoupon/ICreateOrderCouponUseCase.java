package com.robinfood.app.usecases.createordercoupon;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;

import java.util.concurrent.CompletableFuture;

/**
 * This use case saves the coupons
 */
public interface ICreateOrderCouponUseCase {

    /**
     * This method saves the coupons in database.
     *
     * @param requestOrderTransactionDTO  request order transaction.
     * @return A completable future boolean.
     */
    CompletableFuture<Boolean> invoke(RequestOrderTransactionDTO requestOrderTransactionDTO);

}
