package com.robinfood.core.mappers;

import com.robinfood.core.dtos.MenuHallsDTO;
import com.robinfood.core.dtos.MenuProductDTO;
import com.robinfood.core.entities.MenuHallsEntity;
import com.robinfood.core.entities.MenuProductEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MenuHallsMappers {

    private MenuHallsMappers() {
        throw new IllegalStateException("Utility class");
    }

    private static List<MenuProductDTO> convertFromEntityToDtoByMenuProductDTO(
            List<MenuProductEntity> menuProductEntities
    ) {
        return Optional.ofNullable(menuProductEntities)
                .orElse(Collections.emptyList()).stream()
                .map(MenuProductMappers::toMenuProductDTO)
                .collect(Collectors.toList());
    }

    public static MenuHallsDTO toMenuHallsDTO(
            MenuHallsEntity menuHallsEntity
    ) {

        return new MenuHallsDTO(
                menuHallsEntity.getId(),
                convertFromEntityToDtoByMenuProductDTO(
                        menuHallsEntity.getProducts()
                ),
                menuHallsEntity.getName(),
                menuHallsEntity.getPosition()
        );
    }
}
