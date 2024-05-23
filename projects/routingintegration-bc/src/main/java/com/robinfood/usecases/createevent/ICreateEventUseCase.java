package com.robinfood.usecases.createevent;

import com.robinfood.dtos.createeventflow.request.EventRequestDTO;
import com.robinfood.dtos.createeventflow.response.FlowEventLogsResponseDTO;

public interface ICreateEventUseCase {
    FlowEventLogsResponseDTO invoke(EventRequestDTO eventRequestDTO);
}
