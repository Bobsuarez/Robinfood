package com.robinfood.app.usecases.createorderaddress;

import com.robinfood.core.dtos.OrderAddressDTO;

import java.util.concurrent.CompletableFuture;

public interface ICreateOrderAddressUseCase {

    /**
     * This use case allows to save the address of the customer
     *
     * @param orderAddressDTO order address field
     * @return a boolean if the transaction and address field qre successful
     */
    CompletableFuture<Boolean> invoke(OrderAddressDTO orderAddressDTO);

}
