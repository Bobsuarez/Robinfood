package com.robinfood.repository.productfinancecategory;

import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryResponseDTO;
import com.robinfood.core.mappers.ProductFinanceCategoryMappers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of IProductFinanceCategoryRepository
 */
@Slf4j
public class ProductFinanceCategoryRepository implements IProductFinanceCategoryRepository {

    private final IProductFinanceCategoryRemoteDataSource productFinanceCategoryRemoteDataSource;

    public ProductFinanceCategoryRepository(
            IProductFinanceCategoryRemoteDataSource productFinanceCategoryRemoteDataSource) {
        this.productFinanceCategoryRemoteDataSource = productFinanceCategoryRemoteDataSource;
    }

    @Override
    public CompletableFuture<List<ProductFinanceCategoryResponseDTO>> getProductsFinanceCategories(
            String token,
            List<Long> productIds
    ) {
        log.info("Going out to get products finance categories with product ids: {}", objectToJson(productIds));

        return productFinanceCategoryRemoteDataSource.getProductsFinanceCategories(token, productIds).thenApply(
                ProductFinanceCategoryMappers::toProductFinanceCategoryResponseDTOList
        );
    }
}
