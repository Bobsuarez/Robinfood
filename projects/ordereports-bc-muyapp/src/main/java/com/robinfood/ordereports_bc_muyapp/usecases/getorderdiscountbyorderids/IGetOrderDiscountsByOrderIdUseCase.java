package com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyorderids;

import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IGetOrderDiscountsByOrderIdUseCase {

    /**
     * Method that obtains the discounts of an order by its orderId
     *
     * @param orderId To consult
     *
     * @return {@link List<OrderDiscountDTO>}
     */
    CompletableFuture<List<OrderDiscountDTO>> invoke(Integer orderId);

}
