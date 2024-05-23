package com.robinfood.app.usecases.getordersbytransaction;

import com.robinfood.core.dtos.getordersbytransaction.OrdersByTransactionResponseDTO;

import java.util.List;

/**
 * Use case that returns the orders associated with a transaction
 */
public interface IGetOrdersByTransactionUseCase {
    /**
     *
     * @param token token from SSO
     * @param transactionId transaction id associated with orders
     * @return orders associated with that transaction
     */
    List<OrdersByTransactionResponseDTO> invoke(String token, Long transactionId);
}
