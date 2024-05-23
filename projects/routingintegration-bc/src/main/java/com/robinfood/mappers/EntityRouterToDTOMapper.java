package com.robinfood.mappers;

import com.robinfood.dtos.getrouters.response.RouterResponseDTO;
import com.robinfood.entities.FlowsEntity;
import com.robinfood.entities.RoutesEntity;

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
