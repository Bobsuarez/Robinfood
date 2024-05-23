package com.robinfood.repository.productfinancecategory;

import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryResponseEntity;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Remote data source that handles all product finance category related data
 */
public interface IProductFinanceCategoryRemoteDataSource {

    /**
     * Retrieves the finance category for some final products
     * @param token the authorization token
     * @param productIds the products ids that will request their finance category
     * @return the final product finance category
     */
    CompletableFuture<List<ProductFinanceCategoryResponseEntity>> getProductsFinanceCategories(
            String token,
            List<Long> productIds
    );
}
