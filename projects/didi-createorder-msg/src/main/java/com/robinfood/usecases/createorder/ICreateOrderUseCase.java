package com.robinfood.usecases.createorder;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;

import java.util.Map;

public interface ICreateOrderUseCase {

    OrderToCreateDTO invoke(OrderToCreateDTO orderToCreateDTO, String token, Map<String, String> messageAttribute);
}
