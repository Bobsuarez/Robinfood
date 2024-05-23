package com.robinfood.mappers;

import com.robinfood.dtos.geteventflow.response.ResponseEventFlowDTO;
import com.robinfood.entities.FlowEventLogsEntity;

public class EntityFlowEventLogsDTOMapper {

    public static ResponseEventFlowDTO buildToEventLogsDTO(FlowEventLogsEntity flowsFlowEventLogsEntity) {

        return ResponseEventFlowDTO.builder()
                .eventId(flowsFlowEventLogsEntity.getEvent_id())
                .flowId(flowsFlowEventLogsEntity.getFlow_id())
                .id(flowsFlowEventLogsEntity.getId())
                .payload(flowsFlowEventLogsEntity.getPayload())
                .build();
    }
}
