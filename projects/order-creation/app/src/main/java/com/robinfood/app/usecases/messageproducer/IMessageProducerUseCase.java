package com.robinfood.app.usecases.messageproducer;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import javax.validation.constraints.NotNull;

/**
 * Use case that sends messages to Topic Orders.Response.Topic
 */
public interface IMessageProducerUseCase {

    void invoke(@NotNull final OrderToCreateDTO orderToCreateDTO, String messageFrom, String messageCountry);

}
