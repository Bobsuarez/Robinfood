package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.GroupDTO;
import com.robinfood.core.entities.GroupEntity;

import static kotlin.collections.CollectionsKt.map;

public final class GroupMappers {

    private GroupMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static GroupEntity toGroupEntity(GroupDTO groupDTO) {
        if (groupDTO.getPortions().isEmpty()) {
            return null;
        }
        return new GroupEntity(
                groupDTO.getId(),
                map(groupDTO.getPortions(), PortionMappers::toPortionEntity),
                groupDTO.getSku()
        );
    }
}
