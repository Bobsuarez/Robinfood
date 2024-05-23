package com.robinfood.mocks.dtos.v1.request;

import com.robinfood.dtos.v1.request.SearchResolutionsDTO;
import com.robinfood.enums.OrderByEnum;

public class SearchResolutionsDTOMock {

    public static SearchResolutionsDTO build() {
        return SearchResolutionsDTO.builder()
                .orderByEnum(OrderByEnum.ASC)
                .page(1)
                .size(10)
                .status(Boolean.TRUE)
                .valueCustomFilter("Resolution")
                .build();
    }

    public static SearchResolutionsDTO buildWithoutPage() {
        return SearchResolutionsDTO.builder()
                .size(10)
                .status(Boolean.TRUE)
                .valueCustomFilter("Resolution")
                .build();
    }

    public static SearchResolutionsDTO buildWithoutSize() {
        return SearchResolutionsDTO.builder()
                .page(1)
                .status(Boolean.TRUE)
                .valueCustomFilter("Resolution")
                .build();
    }

}
