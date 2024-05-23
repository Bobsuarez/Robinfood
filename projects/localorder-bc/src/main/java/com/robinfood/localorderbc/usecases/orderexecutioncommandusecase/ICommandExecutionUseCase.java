package com.robinfood.localorderbc.usecases.orderexecutioncommandusecase;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;

public interface ICommandExecutionUseCase {

    OrderCommandExecutionDTO invoke(OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO);

}
