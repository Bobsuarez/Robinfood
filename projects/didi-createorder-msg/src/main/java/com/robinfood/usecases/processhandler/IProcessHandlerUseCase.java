package com.robinfood.usecases.processhandler;

import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;

import java.util.Map;

public interface IProcessHandlerUseCase {

    void invoke(OrderToCreateDTO orderToCreateDTO, Map<String, String> messageAttribute);

}
