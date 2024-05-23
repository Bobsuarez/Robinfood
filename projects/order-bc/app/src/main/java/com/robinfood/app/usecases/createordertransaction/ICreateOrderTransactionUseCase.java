package com.robinfood.app.usecases.createordertransaction;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;
import com.robinfood.core.exceptions.CannotDivideByZeroException;

/**
 * Use case that create order transaction
 */
public interface ICreateOrderTransactionUseCase {

    /**
     * Retrieve the order transaction object
     * @param orderTransactionDTO List of order transactions
     * @return Order transaction object
     * @throws CannotDivideByZeroException
     */
    ResponseCreatedOrderTransactionDTO invoke(
            RequestOrderTransactionDTO orderTransactionDTO
    ) throws CannotDivideByZeroException;
}
