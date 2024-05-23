package com.robinfood.mappers;

import com.robinfood.dtos.getrouters.response.FlowResponseDTO;
import com.robinfood.entities.FlowsEntity;

public class EntityToFlowResponseDTOMapper {

    public static FlowResponseDTO buildToFlowResponseDTO(FlowsEntity flowsEntity) {

        return FlowResponseDTO.builder()
                .code(flowsEntity.getCode())
                .flowId(flowsEntity.getId())
                .name(flowsEntity.getName())
                .build();
    }
}
