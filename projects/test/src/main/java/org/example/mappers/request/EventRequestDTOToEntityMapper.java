package org.example.mappers.request;

import org.example.dtos.createeventflow.request.EventRequestDTO;
import org.example.entities.FlowEventLogsEntity;

public class EventRequestDTOToEntityMapper {

    public static FlowEventLogsEntity buildToFlowEventLogsEntity(EventRequestDTO eventRequestDTO) {

        return FlowEventLogsEntity.builder()
                .event_id(eventRequestDTO.getEventId())
                .payload(eventRequestDTO.getPayload())
                .flow_id(eventRequestDTO.getFlowId())
                .build();
    }
}
