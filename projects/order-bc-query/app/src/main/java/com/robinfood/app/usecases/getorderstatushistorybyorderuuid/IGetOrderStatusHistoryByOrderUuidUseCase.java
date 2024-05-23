package com.robinfood.app.usecases.getorderstatushistorybyorderuuid;

import com.robinfood.core.dtos.orderstatushistory.OrderStatusHistoryDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IGetOrderStatusHistoryByOrderUuidUseCase {

    List<OrderStatusHistoryDTO> invoke(String uuid) throws ResourceNotFoundException;
}
