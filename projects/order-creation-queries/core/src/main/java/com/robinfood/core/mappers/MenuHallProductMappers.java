package com.robinfood.core.mappers;

import com.robinfood.core.dtos.menu.MenuGroupPortionDTO;
import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.dtos.menu.MenuProductGroupDTO;
import com.robinfood.core.entities.menu.MenuGroupPortionEntity;
import com.robinfood.core.entities.menu.MenuHallProductResponseEntity;
import com.robinfood.core.entities.menu.MenuProductGroupEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;

public final class MenuHallProductMappers {

    private MenuHallProductMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static MenuHallProductResponseDTO toMenuHallProductResponseDTO(
            MenuHallProductResponseEntity menuHallProductResponseEntity
    ) {

        return new MenuHallProductResponseDTO(
                menuHallProductResponseEntity.getArticleId(),
                menuHallProductResponseEntity.getBrandId(),
                menuHallProductResponseEntity.getDescription(),
                menuHallProductResponseEntity.getDiscount(),
                menuHallProductResponseEntity.getDisplayType(),
                getMenuProductGroupDTOS(
                        menuHallProductResponseEntity),
                menuHallProductResponseEntity.getId(),
                menuHallProductResponseEntity.getImage(),
                menuHallProductResponseEntity.getName(),
                menuHallProductResponseEntity.getParentId(),
                menuHallProductResponseEntity.getPosition(),
                menuHallProductResponseEntity.getPrice(),
                menuHallProductResponseEntity.getProductFlow(),
                menuHallProductResponseEntity.getSizeId(),
                menuHallProductResponseEntity.getSku(),
                menuHallProductResponseEntity.getTags(),
                menuHallProductResponseEntity.getType(),
                menuHallProductResponseEntity.getTypeName()
        );
    }

    private static List<MenuProductGroupDTO> getMenuProductGroupDTOS(
            MenuHallProductResponseEntity menuHallProductResponseEntity) {

        if (Objects.isNull(menuHallProductResponseEntity.getGroups())) {
            return new ArrayList<>();
        }

        return CollectionsKt.map(
                menuHallProductResponseEntity.getGroups(),
                MenuHallProductMappers::toMenuProductGroupDTO
        );
    }

    public static MenuProductGroupDTO toMenuProductGroupDTO(
            MenuProductGroupEntity menuProductGroupEntity
    ) {
        
        return new MenuProductGroupDTO(
                menuProductGroupEntity.getFree(),
                menuProductGroupEntity.getGroupTypeId(),
                menuProductGroupEntity.getId(),
                menuProductGroupEntity.getMax(),
                menuProductGroupEntity.getMin(),
                menuProductGroupEntity.getNamePlural(),
                menuProductGroupEntity.getNameSingular(),
                getPortions(menuProductGroupEntity),
                menuProductGroupEntity.getNamePlural(),
                menuProductGroupEntity.getNameSingular(),
                menuProductGroupEntity.getSku(),
                menuProductGroupEntity.getSubsidy()

        );
    }

    private static List<MenuGroupPortionDTO> getPortions(MenuProductGroupEntity menuProductGroupEntity) {

        if (Objects.isNull(menuProductGroupEntity.getPortions())) {
            return new ArrayList<>();
        }

        return CollectionsKt.map(
                menuProductGroupEntity.getPortions(),
                MenuHallProductMappers::toMenuGroupPortionDTO);
    }

    public static MenuGroupPortionDTO toMenuGroupPortionDTO(MenuGroupPortionEntity menuGroupPortionEntity) {

        return new MenuGroupPortionDTO(
                menuGroupPortionEntity.getIsDefault(),
                menuGroupPortionEntity.getDiscount(),
                menuGroupPortionEntity.getId(),
                menuGroupPortionEntity.getImage(),
                menuGroupPortionEntity.getName(),
                menuGroupPortionEntity.getParentId(),
                menuGroupPortionEntity.getPremiumPrice(),
                menuGroupPortionEntity.getPrice(),
                menuGroupPortionEntity.getPosition(),
                menuGroupPortionEntity.getSku(),
                menuGroupPortionEntity.getUnit(),
                menuGroupPortionEntity.getWeight()
        );
    }
}
