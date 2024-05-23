package com.robinfood.core.mappers;

import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductCategoryDTO;

public final class CategoryMappers {

    private CategoryMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static FinalProductCategoryDTO toFinalProductCategoryDTO(
        ProductFinanceCategoryResponseDTO productFinanceCategoryResponseDTO
    ) {
        return new FinalProductCategoryDTO(
            productFinanceCategoryResponseDTO.getCategory().getId(),
            productFinanceCategoryResponseDTO.getCategory().getName()
        );
    }
}
