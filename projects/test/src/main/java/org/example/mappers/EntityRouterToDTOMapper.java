package org.example.mappers;

import org.example.dtos.getrouters.response.RouterResponseDTO;
import org.example.entities.FlowsEntity;
import org.example.entities.RoutesEntity;

public class EntityRouterToDTOMapper {

    public RouterResponseDTO buildToRouterResponseDTO(FlowsEntity flowsEntity, RoutesEntity routesEntity) {

        return RouterResponseDTO.builder()
                .channelId(routesEntity.getChannel_id())
                .description(routesEntity.getDescription())
                .flowResponseDTO(EntityToFlowResponseDTOMapper
                        .buildToFlowResponseDTO(flowsEntity))
                .idRouter(routesEntity.getId())
                .name(routesEntity.getName())
                .url(routesEntity.getUrl())
                .build();
    }
}
