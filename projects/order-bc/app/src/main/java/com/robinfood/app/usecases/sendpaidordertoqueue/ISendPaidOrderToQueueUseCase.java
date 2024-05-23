package com.robinfood.app.usecases.sendpaidordertoqueue;

public interface ISendPaidOrderToQueueUseCase {

    /**
     * Method that writes a message to the paid order notification queue
     *
     * @param orderId: order id to check your information
     */
    void invoke(Long orderId);

}
