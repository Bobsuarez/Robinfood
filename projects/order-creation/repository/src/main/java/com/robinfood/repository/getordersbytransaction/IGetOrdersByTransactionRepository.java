package com.robinfood.repository.getordersbytransaction;

import com.robinfood.core.dtos.getordersbytransaction.OrdersByTransactionResponseDTO;

import java.util.List;

public interface IGetOrdersByTransactionRepository {

    /**
     * Returns the orders associated with a transaction
     *
     * @param transactionId transaction id associated with orders
     * @param token the authorization token
     * @return a list that contains the result of orders associated with a transaction
     */
    List<OrdersByTransactionResponseDTO> invoke(Long transactionId, String token);
}
