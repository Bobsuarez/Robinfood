package com.robinfood.mappers;

import com.robinfood.dtos.getconfigsubscribers.reponse.PropertiesItemDTO;
import com.robinfood.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import com.robinfood.entities.SubscriberTypesEntity;
import com.robinfood.entities.SubscribersEntity;

import java.util.List;

public class EntitySubscriberItemToDTOMapper {

    public static SubscribersItemDTO buildToSubscribersChannelDTO(
            List<PropertiesItemDTO> propertiesItemDTOS,
            SubscribersEntity subscribersEntity,
            SubscriberTypesEntity subscriberTypesEntity
    ) {

        return SubscribersItemDTO.builder()
                .description(subscribersEntity.getDescription())
                .name(subscribersEntity.getName())
                .properties(propertiesItemDTOS)
                .subscriberId(subscribersEntity.getId())
                .type(EntitySubscriberTypeToDTOMapper.buildToTypeDTO(subscriberTypesEntity))
                .build();
    }
}
