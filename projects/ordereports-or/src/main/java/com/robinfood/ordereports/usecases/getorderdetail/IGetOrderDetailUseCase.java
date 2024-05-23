package com.robinfood.ordereports.usecases.getorderdetail;

import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;

public interface IGetOrderDetailUseCase {

    OrderDetailDTO invoke(String transactionUuid);
}
