package com.robinfood.app.usecases.getorderaddressbyorderid;

import com.robinfood.core.dtos.OrderAddressDTO;

public interface IGetOrderAddressByOrderIdUseCase {

    /**
     * Method that finds the address of an order by its orderId
     *
     * @param orderId   To consult
     * @return {@link OrderAddressDTO}
     */
    OrderAddressDTO invoke(Long orderId);

}
