package com.robinfood.mappers;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.entities.ChangeStatusEntity;
import com.robinfood.utils.ObjectMapperSingleton;

public class ChangeStatusMapper {

    private ChangeStatusMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ChangeStatusDTO eventStringToChangeStatusDto(String event) {
        return ObjectMapperSingleton.jsonToClass(event, ChangeStatusDTO.class);
    }

    public static ChangeStatusEntity changeStatusDtoToChangeStatusEntity(
            ChangeStatusDTO changeStatusDTO,
            String eventId
    ) {

        ChangeStatusEntity changeStatusEntity =
                ObjectMapperSingleton.objectToClassConvertValue(changeStatusDTO, ChangeStatusEntity.class);

        changeStatusEntity.setEventId(eventId);
        return changeStatusEntity;
    }
}
