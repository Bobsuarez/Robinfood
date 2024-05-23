package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionrequestdto.FinalProductCategoryDTO;

public class FinalProductCategoryDTOMocks {

    public static FinalProductCategoryDTO build() {
        return FinalProductCategoryDTO.builder()
                .id(1L)
                .name("main courses")
                .build();
    }
}
