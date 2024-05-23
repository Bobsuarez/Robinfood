package com.robinfood.changestatusbc.usecases.changeorderfinalproductportions;

/**
 *
 */
public interface IChangeOrderFinalProductPortionsUseCase {

    /**
     * use case that changes the effective_sale field in the order_final_product_portions table,
     * 1 when the sale is effective, 0 otherwise
     *
     * @param orderId order to evaluate
     * @param status indicates the status of the order
     */
    void invoke(Long orderId, String status);
}
