package com.robinfood.network.api;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

/**
 * Connections to Order Sync Data Business Capability
 */
public interface OrderSyncDataBCAPI {

    /**
     * Connects to an endpoint and returns the finance categories for products given
     * @param productIds the products ids
     * @param token the authorization token
     * @return a future containing the finance category information
     */
    @GET("v3/finance-categories")
    Call<ApiResponseEntity<List<ProductFinanceCategoryResponseEntity>>> getProductsFinanceCategories(
            @Query(value = "productIds", encoded = true) String productIds,
            @Header(AUTHORIZATION_HEADER_KEY) String token
    );

}
