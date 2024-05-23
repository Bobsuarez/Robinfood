package com.robinfood.ordereports.repositories.orderdetail;

import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;

public interface IGetOrderDetailRepository {

    OrderDetailDTO getOrderDetail(String token, String transactionUuid);
}
