package com.robinfood.app.usecases.sendmsgacceptedorrejectedtoqueue;

import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;

/**
 * Use case that processes a payment method from the activemq message consumer
 */
public interface ISendMsgAcceptedOrRejectedToQueueUseCase {

    /**
     * processes a payment method from the activemq message consumer
     *
     * @param transactionDTO object with the payment-method information
     */
    void invoke(TransactionDTO transactionDTO);
}
