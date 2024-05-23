package com.robinfood.core.mappers;

import com.robinfood.core.dtos.MenuProductDTO;
import com.robinfood.core.entities.MenuProductEntity;

public final class MenuProductMappers {

    private MenuProductMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static MenuProductDTO toMenuProductDTO(MenuProductEntity menuProductEntity) {
        return new MenuProductDTO(
                menuProductEntity.getBrandId(),
                menuProductEntity.getDescription(),
                menuProductEntity.getDiscount(),
                menuProductEntity.getDisplayType(),
                menuProductEntity.getId(),
                menuProductEntity.getImage(),
                menuProductEntity.getName(),
                menuProductEntity.getParentId(),
                menuProductEntity.getPosition(),
                menuProductEntity.getPrice(),
                menuProductEntity.getProductFlow(),
                menuProductEntity.getSizeId(),
                menuProductEntity.getSku(),
                menuProductEntity.getTypeId()
        );
    }
}
