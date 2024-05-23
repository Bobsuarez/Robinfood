package com.robinfood.app.usecases.getordersbytransaction;

import com.robinfood.core.dtos.OrderDTO;

import java.util.List;

/**
 * Use case that returns the orders associated with a transaction
 */
public interface IGetOrdersByTransactionIdUseCase {

    /**
     *  the orders associated with a transaction
     *
     * @param transactionId transaction id associated with orders
     * @return orders associated with that transaction
     */
    List<OrderDTO> invoke(Long transactionId);
}
