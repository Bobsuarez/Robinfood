package com.robinfood.localorderbc.usecases.orderexecutioncommandtracelogsusecase;

import com.robinfood.localorderbc.dtos.request.OrderCommandExecutionRequestDTO;

public interface ICommandExecutionTraceLogsUseCase {

    void invoke(OrderCommandExecutionRequestDTO orderCommandExecutionRequestDTO, Integer numberOfRetries);

}
