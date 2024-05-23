package com.robinfood.localorderbc.usecases.getorderexecutioncommandusecase;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;

import java.util.List;

public interface IGetOrderCommandExecutionUseCase {

    List<OrderCommandExecutionDTO> invoke();
}
