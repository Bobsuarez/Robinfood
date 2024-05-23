package com.robinfood.mappers;

import com.robinfood.dtos.getconfigsubscribers.reponse.FlowDTO;
import com.robinfood.entities.FlowsEntity;

public class EntityFlowsToDTOMapper {

    public static FlowDTO buildToFlowDTO(FlowsEntity flowsEntity) {

        return FlowDTO.builder()
                .code(flowsEntity.getCode())
                .flowId(flowsEntity.getId())
                .name(flowsEntity.getName())
                .build();
    }
}
