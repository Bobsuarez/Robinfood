package com.robinfood.core.mappers;

import com.robinfood.core.dtos.menu.MenuBrandDTO;
import com.robinfood.core.entities.menu.MenuBrandEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class MenuBrandStoreMappers {

    private MenuBrandStoreMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static List<MenuBrandDTO> menuBrandEntityToMenuBrandStoreDTOList(
            List<MenuBrandEntity> menuBrandEntities
    ) {
        return menuBrandEntities.stream().map(
                MenuBrandStoreMappers::menuBrandEntityToMenuBrandStoreDTO
        ).collect(Collectors.toList());
    }

    public static MenuBrandDTO menuBrandEntityToMenuBrandStoreDTO(MenuBrandEntity menuBrandEntity) {
        return MenuBrandDTO.builder()
                .color(menuBrandEntity.getColor())
                .countryId(menuBrandEntity.getCountryId())
                .franchiseId(menuBrandEntity.getFranchiseId())
                .id(menuBrandEntity.getId())
                .image(menuBrandEntity.getImage())
                .name(menuBrandEntity.getName())
                .storeBrand(MenuStoreBrandMappers.menuStoreBrandEntityToMenuStoreBrandDTO(
                        menuBrandEntity.getStoreBrand()
                ))
                .build();
    }
}
