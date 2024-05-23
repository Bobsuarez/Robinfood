package com.robinfood.app.mappers;

import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.entities.OrderFinalProductEntity;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_EMPTY_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_EMPTY_VALUE;
import static com.robinfood.core.constants.GlobalConstants.REPORT_CATEGORY_SERVICES;
import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

public class OrderCategoryMapper {

    public static OrderCategoryDTO informationToOrderCategoryDTO(
            Double compensation,
            Double discounts,
            Double grossValue,
            Double netValue,
            OrderFinalProductEntity orderFinalProductEntity,
            Double taxes
    ) {

        return OrderCategoryDTO.builder()
                .compensation(compensation)
                .discounts(discounts)
                .grossValue(grossValue)
                .id(orderFinalProductEntity.getFinalProductCategoryId())
                .name(capitalizeFully(orderFinalProductEntity.getFinalProductCategoryName()))
                .netValue(netValue)
                .taxes(taxes)
                .build();
    }

    public static OrderCategoryDTO informationServicesToOrderCategoryDTO(
            Double discounts,
            Double grossValue,
            Double netValue,
            Double taxes
    ) {

        return OrderCategoryDTO.builder()
                .compensation(DEFAULT_DOUBLE_EMPTY_VALUE)
                .discounts(discounts)
                .grossValue(grossValue)
                .id(DEFAULT_LONG_EMPTY_VALUE)
                .name(REPORT_CATEGORY_SERVICES)
                .netValue(netValue)
                .taxes(taxes)
                .build();
    }

    private OrderCategoryMapper() {
        throw new IllegalStateException("Utility class");
    }
}
