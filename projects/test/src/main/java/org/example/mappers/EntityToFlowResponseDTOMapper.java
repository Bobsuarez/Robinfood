package org.example.mappers;

import org.example.dtos.getrouters.response.FlowResponseDTO;
import org.example.entities.FlowsEntity;

public class EntityToFlowResponseDTOMapper {

    public static FlowResponseDTO buildToFlowResponseDTO(FlowsEntity flowsEntity) {

        return FlowResponseDTO.builder()
                .code(flowsEntity.getCode())
                .flowId(flowsEntity.getId())
                .name(flowsEntity.getName())
                .build();
    }
}
