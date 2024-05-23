package com.robinfood.usecases.geteventsflow;

import com.robinfood.dtos.geteventflow.request.EventFlowRequestDTO;
import com.robinfood.dtos.geteventflow.response.ResponseEventFlowDTO;
import com.robinfood.entities.FlowEventLogsEntity;
import com.robinfood.entities.FlowsEntity;
import com.robinfood.mappers.EntityFlowEventLogsDTOMapper;
import com.robinfood.repository.floweventlogs.FlowEventLogsRepository;
import com.robinfood.repository.floweventlogs.IFlowEventLogsRepository;
import com.robinfood.repository.flows.FlowsRepository;
import com.robinfood.repository.flows.IFlowsRepository;
import lombok.AllArgsConstructor;

/**
 * Use case that queries the information of the events by flow
 */
@AllArgsConstructor
public class GetEventsFlowUseCase implements IGetEventsFlowUseCase {

    private final IFlowsRepository flowsRepository;
    private final IFlowEventLogsRepository flowEventLogsRepository;

    public GetEventsFlowUseCase() {
        flowsRepository = FlowsRepository.getInstance();
        flowEventLogsRepository = FlowEventLogsRepository.getInstance();
    }

    @Override
    public ResponseEventFlowDTO invoke(EventFlowRequestDTO eventFlowRequestDTO) {

        FlowsEntity flowsEntity = flowsRepository.searchByFlowCode(eventFlowRequestDTO.getFlowCode());

        FlowEventLogsEntity flowEventLogsEntity =
                flowEventLogsRepository.searchByEventIdAndFlowId(eventFlowRequestDTO.getEventId(),
                        flowsEntity.getId());

        return EntityFlowEventLogsDTOMapper.buildToEventLogsDTO(flowEventLogsEntity);
    }
}
