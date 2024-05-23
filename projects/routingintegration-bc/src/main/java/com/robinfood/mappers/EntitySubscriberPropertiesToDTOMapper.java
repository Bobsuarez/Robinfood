package com.robinfood.mappers;

import com.robinfood.dtos.getconfigsubscribers.reponse.PropertiesItemDTO;
import com.robinfood.entities.SubscriberPropertiesEntity;

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
