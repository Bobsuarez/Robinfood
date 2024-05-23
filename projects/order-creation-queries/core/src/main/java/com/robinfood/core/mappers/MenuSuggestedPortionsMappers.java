package com.robinfood.core.mappers;

import com.robinfood.core.dtos.menu.MenuSuggestedPortionDataDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.entities.menu.MenuSuggestedPortionDataEntity;
import com.robinfood.core.entities.menu.MenuSuggestedPortionResponseEntity;
import kotlin.collections.CollectionsKt;

import java.util.List;

public final class MenuSuggestedPortionsMappers {

    private MenuSuggestedPortionsMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static List<MenuSuggestedPortionResponseDTO> toMenuSuggestedPortionResponseDTOs(
            List<MenuSuggestedPortionResponseEntity> menuSuggestedPortionResponseEntities
    ) {
        return CollectionsKt.map(
                menuSuggestedPortionResponseEntities,
                (MenuSuggestedPortionResponseEntity menuSuggestedPortionResponseEntity) ->
                        new MenuSuggestedPortionResponseDTO(
                                toMenuSuggestedPortionDataDTOs(menuSuggestedPortionResponseEntity.getChanges()),
                                menuSuggestedPortionResponseEntity.getId(),
                                menuSuggestedPortionResponseEntity.getImage(),
                                menuSuggestedPortionResponseEntity.getName(),
                                menuSuggestedPortionResponseEntity.getParentId(),
                                menuSuggestedPortionResponseEntity.getSku()
                        )
        );
    }

    public static List<MenuSuggestedPortionDataDTO> toMenuSuggestedPortionDataDTOs(
            List<MenuSuggestedPortionDataEntity> menuSuggestedPortionDataEntities
    ) {
        return CollectionsKt.map(
                menuSuggestedPortionDataEntities,
                (MenuSuggestedPortionDataEntity menuSuggestedPortionDataEntity) -> new MenuSuggestedPortionDataDTO(
                        menuSuggestedPortionDataEntity.getId(),
                        menuSuggestedPortionDataEntity.getImage(),
                        menuSuggestedPortionDataEntity.getName(),
                        menuSuggestedPortionDataEntity.getParentId(),
                        menuSuggestedPortionDataEntity.getSku(),
                        menuSuggestedPortionDataEntity.getDicUnitId(),
                        menuSuggestedPortionDataEntity.getQuantity()
                )
        );
    }
}
