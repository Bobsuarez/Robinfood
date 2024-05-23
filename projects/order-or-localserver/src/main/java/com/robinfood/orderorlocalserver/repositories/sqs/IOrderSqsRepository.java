package com.robinfood.orderorlocalserver.repositories.sqs;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;

public interface IOrderSqsRepository {

    void sendOrderCreatedMessage(OrderDetailDTO orderDetailDTO);

}
