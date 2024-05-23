package com.robinfood.core.mappers;

import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryDTO;
import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryResponseDTO;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryEntity;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryResponseEntity;
import java.util.List;

import static kotlin.collections.CollectionsKt.map;

public final class ProductFinanceCategoryMappers {

    private ProductFinanceCategoryMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static List<ProductFinanceCategoryResponseDTO> toProductFinanceCategoryResponseDTOList(
            List<ProductFinanceCategoryResponseEntity> productFinanceCategoryResponseEntities
    ) {
        return map(
                productFinanceCategoryResponseEntities,
                ProductFinanceCategoryMappers::toProductFinanceCategoryResponseDTO
        );
    }

    private static ProductFinanceCategoryResponseDTO toProductFinanceCategoryResponseDTO(
            ProductFinanceCategoryResponseEntity productFinanceCategoryResponseEntity
    ) {
        return new ProductFinanceCategoryResponseDTO(
                productFinanceCategoryResponseEntity.getId(),
                ProductFinanceCategoryMappers.toProductFinanceCategoryDTO(
                        productFinanceCategoryResponseEntity.getCategory()
                )
        );
    }

    private static ProductFinanceCategoryDTO toProductFinanceCategoryDTO(
            ProductFinanceCategoryEntity productFinanceCategoryEntity
    ) {
        return new ProductFinanceCategoryDTO(
                productFinanceCategoryEntity.getId(),
                productFinanceCategoryEntity.getName()
        );
    }
}
