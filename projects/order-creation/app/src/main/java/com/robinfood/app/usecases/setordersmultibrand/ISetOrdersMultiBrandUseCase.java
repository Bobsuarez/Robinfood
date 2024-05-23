package com.robinfood.app.usecases.setordersmultibrand;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;

import java.util.List;

public interface ISetOrdersMultiBrandUseCase {

    /**
     * Method that is responsible for verifying the products of the orders
     * to identify if it is multi-brand
     *
     * @param orderDTOS orders to verify
     */
    void invoke(List<OrderDTO> orderDTOS);

}
