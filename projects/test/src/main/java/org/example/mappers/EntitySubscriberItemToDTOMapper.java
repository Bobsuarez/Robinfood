package org.example.mappers;


import java.util.List;
import org.example.dtos.getconfigsubscribers.reponse.PropertiesItemDTO;
import org.example.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import org.example.entities.SubscriberTypesEntity;
import org.example.entities.SubscribersEntity;

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
