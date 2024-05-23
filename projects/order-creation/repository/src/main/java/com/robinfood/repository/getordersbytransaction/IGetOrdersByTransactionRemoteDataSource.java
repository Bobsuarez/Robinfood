package com.robinfood.repository.getordersbytransaction;

import com.robinfood.core.entities.getordersbytransaction.OrdersByTransactionEntity;

import java.util.List;

public interface IGetOrdersByTransactionRemoteDataSource {

    /**
     * Returns the orders associated with a transaction
     *
     * @param transactionId transaction id associated with orders
     * @param token the authorization token
     * @return a List that contains the result of orders associated with a transaction
     */
    List<OrdersByTransactionEntity>invoke(Long transactionId, String token);
}
