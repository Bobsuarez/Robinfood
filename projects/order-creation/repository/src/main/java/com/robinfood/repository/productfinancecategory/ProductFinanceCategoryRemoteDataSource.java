package com.robinfood.repository.productfinancecategory;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.extensions.ListExtensionsKt;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.logging.mappeddiagnosticcontext.CreateTransactionCustomLog;
import com.robinfood.network.api.OrderSyncDataBCAPI;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IProductFinanceCategoryRemoteDataSource
 */
@Component
public class ProductFinanceCategoryRemoteDataSource implements IProductFinanceCategoryRemoteDataSource {

    private final OrderSyncDataBCAPI orderSyncDataBCAPI;

    public ProductFinanceCategoryRemoteDataSource(OrderSyncDataBCAPI orderSyncDataBCAPI) {
        this.orderSyncDataBCAPI = orderSyncDataBCAPI;
    }

    @Override
    public CompletableFuture<List<ProductFinanceCategoryResponseEntity>> getProductsFinanceCategories(
            String token,
            List<Long> productIds
    ) {
        final String productsIdsQuery = ListExtensionsKt.toQueryParam(productIds);
        final Result<ApiResponseEntity<List<ProductFinanceCategoryResponseEntity>>> result = NetworkExtensionsKt
                .safeAPICall(orderSyncDataBCAPI.getProductsFinanceCategories(productsIdsQuery, token));
        if (result instanceof Result.Error) {
            final Result.Error error = ((Result.Error) result);
            throw new TransactionCreationException(
                    error.getHttpStatus(),
                    error.getException().getLocalizedMessage(),
                    TransactionCreationErrors.GET_CATEGORY_ORDER_BC_SYNC_DATA_ERROR
            );
        }
        CreateTransactionCustomLog.removeHits();
        return CompletableFuture.completedFuture(
                ((Result.Success<ApiResponseEntity<List<ProductFinanceCategoryResponseEntity>>>) result)
                        .getData()
                        .getData()
        );
    }
}
