package org.example.mappers;

import org.example.dtos.createeventflow.response.FlowEventLogsResponseDTO;
import org.example.entities.FlowEventLogsEntity;

public class EntityFlowEventLogsToDTOMapper {

    public static FlowEventLogsResponseDTO buildToFlowEventLogsDTO(
            FlowEventLogsEntity flowEventLogsEntity,
            String uuid
    ) {

        return FlowEventLogsResponseDTO.builder()
                .eventId(flowEventLogsEntity.getEvent_id())
                .flowId(flowEventLogsEntity.getFlow_id())
                .id(flowEventLogsEntity.getId())
                .payload(flowEventLogsEntity.getPayload())
                .uuid(uuid)
                .build();
    }
}
