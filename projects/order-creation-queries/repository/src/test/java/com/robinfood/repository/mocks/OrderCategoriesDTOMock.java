package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;

import java.util.List;

public class OrderCategoriesDTOMock {

    public static List<OrderCategoryDTO> getDataOrderCategoryList() {

        return  List.of(OrderCategoryDTO.builder()
                        .compensation(100000.0)
                        .discounts(100.0)
                        .grossValue(100.0)
                        .id(1)
                        .name("Bebidas")
                        .netValue(100.0)
                        .taxes(100.0)
                        .build());
    }
}
