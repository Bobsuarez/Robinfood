package com.robinfood.app.usecases.createorder;

import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.exceptions.CannotDivideByZeroException;

import java.util.List;

/**
 * Use case that creates the order
 */
public interface ICreateOrderUseCase {

    /**
     * Retrieves result list of the created orders
     * @param orderTransactionDTO info of transaction
     * @param orderDTOS list of orders
     * @param transactionId get id of transaction
     * @param totalPaymentMethods total of the payment methods
     * @return List created orders
     */
    List<ResponseCreatedOrderDTO> invoke(
            RequestOrderTransactionDTO orderTransactionDTO,
            List<OrderDTO> orderDTOS,
            Long transactionId,
            Double totalPaymentMethods
    ) throws CannotDivideByZeroException;
}
