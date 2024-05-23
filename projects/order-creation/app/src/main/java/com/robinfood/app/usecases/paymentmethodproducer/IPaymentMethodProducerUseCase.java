package com.robinfood.app.usecases.paymentmethodproducer;

import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;

/**
 * Use case that sends messages to Orders.ChangeStatus.Queue
 */
public interface IPaymentMethodProducerUseCase {
    void invoke(ChangeOrderStatusDTO changeOrderStatusDTO);
}
