package com.robinfood.orderorlocalserver.usecases.processordercreatedfromconsumer;

import com.robinfood.orderorlocalserver.dtos.orderqueue.OrderCreatedQueueDTO;
import com.robinfood.orderorlocalserver.exceptions.GetOrderDetailFailedException;

public interface IProcessOrderCreatedFromConsumerUseCase {

    void invoke(OrderCreatedQueueDTO orderCreatedQueueDTO) throws GetOrderDetailFailedException;
}
