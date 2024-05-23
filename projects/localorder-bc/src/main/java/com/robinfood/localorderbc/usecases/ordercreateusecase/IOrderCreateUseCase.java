package com.robinfood.localorderbc.usecases.ordercreateusecase;

import com.robinfood.localorderbc.dtos.OrderDTO;

public interface IOrderCreateUseCase {

    OrderDTO invoke(OrderDTO orderDTO);

    OrderDTO update(Long orderId, Long statusId);

}
