package org.example.mappers;

import org.example.dtos.getconfigsubscribers.reponse.FlowDTO;
import org.example.entities.FlowsEntity;

public class EntityFlowsToDTOMapper {

    public static FlowDTO buildToFlowDTO(FlowsEntity flowsEntity) {

        return FlowDTO.builder()
                .code(flowsEntity.getCode())
                .flowId(flowsEntity.getId())
                .name(flowsEntity.getName())
                .build();
    }
}
