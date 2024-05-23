package com.robinfood.core.mappers;

import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.dtos.MenuHallsDTO;
import com.robinfood.core.entities.MenuCurrentEntity;
import com.robinfood.core.entities.MenuHallsEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MenuCurrentMappers {

    private MenuCurrentMappers() {
        throw new IllegalStateException("Utility class");
    }

    private static List<MenuHallsDTO> convertFromEntityToDtoByMenuHallsDTOS(
            List<MenuHallsEntity> menuHallsEntities
    ) {
        return Optional.ofNullable(menuHallsEntities)
                .orElse(Collections.emptyList()).stream()
                .map(MenuHallsMappers::toMenuHallsDTO)
                .collect(Collectors.toList());
    }

    public static MenuCurrentDTO menuCurrentDTO(
            MenuCurrentEntity menuCurrentEntity
    ) {

        return new MenuCurrentDTO(
                convertFromEntityToDtoByMenuHallsDTOS(menuCurrentEntity.getHalls()),
                menuCurrentEntity.getName()
        );
    }
}
