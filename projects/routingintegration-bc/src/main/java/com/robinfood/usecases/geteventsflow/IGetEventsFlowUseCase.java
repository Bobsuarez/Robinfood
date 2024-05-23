package com.robinfood.usecases.geteventsflow;

import com.robinfood.dtos.geteventflow.request.EventFlowRequestDTO;
import com.robinfood.dtos.geteventflow.response.ResponseEventFlowDTO;

public interface IGetEventsFlowUseCase {

    ResponseEventFlowDTO invoke(EventFlowRequestDTO eventFlowRequestDTO);
}
