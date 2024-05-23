package com.robinfood.mappers;

import com.robinfood.dtos.getconfigsubscribers.reponse.TypeDTO;
import com.robinfood.entities.SubscriberTypesEntity;

public class EntitySubscriberTypeToDTOMapper {

    public static TypeDTO buildToTypeDTO(SubscriberTypesEntity subscriberTypesEntity) {

        return TypeDTO.builder()
                .description(subscriberTypesEntity.getDescription())
                .name(subscriberTypesEntity.getName())
                .typeId(subscriberTypesEntity.getId())
                .build();
    }
}
