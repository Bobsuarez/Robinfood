package org.example.mappers;

import org.example.dtos.getconfigsubscribers.reponse.TypeDTO;
import org.example.entities.SubscriberTypesEntity;

public class EntitySubscriberTypeToDTOMapper {

    public static TypeDTO buildToTypeDTO(SubscriberTypesEntity subscriberTypesEntity) {

        return TypeDTO.builder()
                .description(subscriberTypesEntity.getDescription())
                .name(subscriberTypesEntity.getName())
                .typeId(subscriberTypesEntity.getId())
                .build();
    }
}
