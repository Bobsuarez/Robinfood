package com.robinfood.ordereports_bc_muyapp.usecases.getorderaddressbyorderid;

import com.robinfood.ordereports_bc_muyapp.dto.OrderAddressDTO;

import java.util.concurrent.CompletableFuture;

public interface IGetOrderAddressByOrderIdUseCase {

    /**
     * Method that finds the address of an order by its orderId
     *
     * @param orderId To consult
     *
     * @return {@link OrderAddressDTO}
     */
    CompletableFuture<OrderAddressDTO> invoke(Integer orderId);

}
