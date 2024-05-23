package com.robinfood.repository.queue;

import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;

/**
 * Repository for the connection with the queue
 */
public interface IProducerOrderRepository {

    /**
     * Sends order created data to activemq topic
     *
     * @param orderCreatedQueueDTO data to be sent corresponding to order created
     */
    void sendOrderCreatedMessage(OrderCreatedQueueDTO orderCreatedQueueDTO);

    /**
     * Sends order created data to activemq topic
     *
     * @param writeChangeStatusDTO data to be sent corresponding to change status order
     */
    void sendChangeStatusMessage(WriteChangeStatusDTO writeChangeStatusDTO);

}
