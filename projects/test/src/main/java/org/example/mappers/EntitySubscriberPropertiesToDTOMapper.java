package org.example.mappers;

import org.example.dtos.getconfigsubscribers.reponse.PropertiesItemDTO;
import org.example.entities.SubscriberPropertiesEntity;

public class EntitySubscriberPropertiesToDTOMapper {

    public static PropertiesItemDTO buildToPropertiesDTO(SubscriberPropertiesEntity propertiesEntity) {

        return PropertiesItemDTO.builder()
                .description(propertiesEntity.getDescription())
                .key(propertiesEntity.getKey())
                .name(propertiesEntity.getValue())
                .propertiesId(propertiesEntity.getId())
                .build();
    }
}
