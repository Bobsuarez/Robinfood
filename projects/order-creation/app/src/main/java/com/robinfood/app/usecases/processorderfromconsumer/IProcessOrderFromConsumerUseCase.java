package com.robinfood.app.usecases.processorderfromconsumer;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;

/**
 * Use case that processes an order from the activemq message consumer
 */
public interface IProcessOrderFromConsumerUseCase {

    void invoke(OrderToCreateDTO orderToCreateDTO, String messageFrom, String messageCountry);

}
