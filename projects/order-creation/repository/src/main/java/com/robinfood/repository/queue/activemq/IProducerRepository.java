package com.robinfood.repository.queue.activemq;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import org.springframework.stereotype.Repository;

/**
 * Repository for the connection with the queue
 */
@Repository
public interface IProducerRepository {

    void sendOrderToCreateMessage(OrderToCreateDTO orderToCreateDTO, String messageFrom, String messageCountry);

    /**
     * Sends order change status data to activemq topic
     *
     * @param changeOrderStatusDTO data to be sent corresponding to change status order
     */
    void sendChangeStatusMessage(ChangeOrderStatusDTO changeOrderStatusDTO);
}
