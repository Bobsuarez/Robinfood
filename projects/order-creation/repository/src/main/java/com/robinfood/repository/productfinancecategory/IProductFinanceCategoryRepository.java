package com.robinfood.repository.productfinancecategory;

import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryResponseDTO;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/**
 * Repository that handles all product finance category related data
 */
public interface IProductFinanceCategoryRepository {

    /**
     * Retrieves the finance category for some final products
     * @param token the authorization token
     * @param productIds the list of products to get category
     * @return the final product finance categories
     */
    @Async
    CompletableFuture<List<ProductFinanceCategoryResponseDTO>> getProductsFinanceCategories(
            String token,
            List<Long> productIds
    );
}
